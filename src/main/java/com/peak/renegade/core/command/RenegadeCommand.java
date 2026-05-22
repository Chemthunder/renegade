package com.peak.renegade.core.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.core.cca.core.WorldInstance;
import com.peak.renegade.core.utility.command.LayerArgumentType;
import com.peak.renegade.core.utility.command.LevelArgumentType;
import com.peak.renegade.game.index.GameLevels;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

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
        );
    }
}
