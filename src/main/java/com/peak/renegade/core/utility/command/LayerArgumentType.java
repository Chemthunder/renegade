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
public class LayerArgumentType implements ArgumentType<LayerArgument> {
    private final LayerStringReader reader;

    public LayerArgumentType(CommandRegistryAccess registryAccess) {
        this.reader = new LayerStringReader(registryAccess);
    }

    public static LayerArgumentType layer(CommandRegistryAccess registryAccess) {
        return new LayerArgumentType(registryAccess);
    }

    public LayerArgument parse(StringReader stringReader) throws CommandSyntaxException {
        LayerStringReader.LayerResult eventResult = this.reader.consume(stringReader);
        return new LayerArgument(eventResult.event());
    }

    public static <S> LayerArgument getLayerArgument(CommandContext<S> context, String name) {
        return context.getArgument(name, LayerArgument.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return this.reader.getSuggestions(builder);
    }
}