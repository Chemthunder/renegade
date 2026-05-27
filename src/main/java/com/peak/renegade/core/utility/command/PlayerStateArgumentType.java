package com.peak.renegade.core.utility.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.peak.renegade.api.game.scene.PlayerState;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class PlayerStateArgumentType extends EnumArgumentType<PlayerState> {
    public PlayerStateArgumentType() {
        super(PlayerState.CODEC, PlayerState::values);
    }

    public static EnumArgumentType<PlayerState> state() {
        return new PlayerStateArgumentType();
    }

    public static PlayerState getState(CommandContext<ServerCommandSource> context, String id) {
        return context.getArgument(id, PlayerState.class);
    }

    public static CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
        for (PlayerState obj : PlayerState.values()) {
            builder.suggest(obj.asString());
        }

        return builder.buildFuture();
    }
}


