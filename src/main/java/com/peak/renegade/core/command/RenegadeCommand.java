package com.peak.renegade.core.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.api.game.scene.ClientScene;
import com.peak.renegade.api.game.scene.GameScene;
import com.peak.renegade.api.game.scene.PlayerState;
import com.peak.renegade.api.utility.RenegadeInstance;
import com.peak.renegade.core.cca.core.HudInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import com.peak.renegade.core.cca.core.WorldInstance;
import com.peak.renegade.core.client.screen.RenegadeTitleScreen;
import com.peak.renegade.core.utility.command.LayerArgumentType;
import com.peak.renegade.core.utility.command.LevelArgumentType;
import com.peak.renegade.core.utility.command.PlayerStateArgumentType;
import com.peak.renegade.game.index.GameLevels;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author Chemthunder
 */
public class RenegadeCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(literal("renegade")
                .then(literal("setLayer").then(argument("layer", LayerArgumentType.layer(commandRegistryAccess)).executes(context -> {
                    GameLayer layer = LayerArgumentType.getLayerArgument(context, "layer").getEvent();

                    WorldInstance.getInstance(context.getSource().getWorld()).setCurrentLayer(layer);
                    return Command.SINGLE_SUCCESS;
                })))

                .then(literal("setLevel").then(argument("level", LevelArgumentType.level(commandRegistryAccess)).executes(context -> {
                    GameLevel level = LevelArgumentType.getLevelArgument(context, "level").getEvent();

                    WorldInstance.getInstance(context.getSource().getWorld()).setCurrentLevel(level);
                    return Command.SINGLE_SUCCESS;
                })))

                .then(literal("getLayer").executes(context -> {
                    WorldInstance instance = WorldInstance.getInstance(context.getSource().getWorld());

                    context.getSource().sendFeedback(() -> Text.translatable("feedback.renegade.layer", instance.getCurrentLayer()), false);
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("getLevel").executes(context -> {
                    WorldInstance instance = WorldInstance.getInstance(context.getSource().getWorld());

                    context.getSource().sendFeedback(() -> Text.translatable("feedback.renegade.level", instance.getCurrentLevel()), false);
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("setRandLevel").executes(context -> {
                    WorldInstance instance = WorldInstance.getInstance(context.getSource().getWorld());

                    instance.setCurrentLevel(GameLevels.LEVELS.get(new Random().nextInt(GameLevels.LEVELS.size())));
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("loadMap").then(argument("layer", LayerArgumentType.layer(commandRegistryAccess)).then(argument("level", LevelArgumentType.level(commandRegistryAccess)).executes(context -> {
                    WorldInstance instance = WorldInstance.getInstance(context.getSource().getWorld());

                    GameLayer layer = LayerArgumentType.getLayerArgument(context, "layer").getEvent();
                    GameLevel level = LevelArgumentType.getLevelArgument(context, "level").getEvent();

                    instance.loadMap(context.getSource().getPlayerOrThrow(), layer, level);
                    return Command.SINGLE_SUCCESS;
                }))))

                .then(literal("start").executes(context -> {
                    HudInstance.getInstance(context.getSource().getPlayer()).end(ClientScene.PLAYING);
                    PlayerInstance.getInstance(context.getSource().getPlayer()).beginCountdown();
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("getPlayerState").executes(context -> {
                    context.getSource().sendFeedback(() -> Text.of(new RenegadeInstance(context.getSource().getPlayer()).player().getState().asString()), false);
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("setPlayerState").then(argument("state", PlayerStateArgumentType.state()).executes(context -> {
                    PlayerState state = PlayerStateArgumentType.getState(context, "state");

                    PlayerInstance.getInstance(context.getSource().getPlayer()).setState(state);
                    return Command.SINGLE_SUCCESS;
                })))
        );
    }
}
