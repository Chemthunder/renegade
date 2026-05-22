package com.peak.renegade.core.utility.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandRegistryAccess;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public class LevelArgumentType implements ArgumentType<LevelArgument> {
    private final LevelStringReader reader;

    public LevelArgumentType(CommandRegistryAccess registryAccess) {
        this.reader = new LevelStringReader(registryAccess);
    }

    public static LevelArgumentType level(CommandRegistryAccess registryAccess) {
        return new LevelArgumentType(registryAccess);
    }

    public LevelArgument parse(StringReader stringReader) throws CommandSyntaxException {
        LevelStringReader.LevelResult eventResult = this.reader.consume(stringReader);
        return new LevelArgument(eventResult.event());
    }

    public static <S> LevelArgument getLevelArgument(CommandContext<S> context, String name) {
        return context.getArgument(name, LevelArgument.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return this.reader.getSuggestions(builder);
    }
}