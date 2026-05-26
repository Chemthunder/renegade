package com.peak.renegade.core.utility.command;

import com.llamalad7.mixinextras.lib.apache.commons.mutable.MutableObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.DynamicOps;
import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.core.index.RenegadeRegistries;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author AcoYT
 */
public class LevelStringReader {
    public static final DynamicCommandExceptionType INVALID_EVENT_ID_EXCEPTION = new DynamicCommandExceptionType(
            id -> Text.stringifiedTranslatable("argument.level.id.invalid", id));

    public static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_DEFAULT = SuggestionsBuilder::buildFuture;

    public final RegistryWrapper.Impl<GameLevel> eventRegistry;
    public final DynamicOps<NbtElement> nbtOps;

    public LevelStringReader(RegistryWrapper.WrapperLookup registryLookup) {
        this.eventRegistry = registryLookup.getOrThrow(RenegadeRegistries.levelKey);
        this.nbtOps = registryLookup.getOps(NbtOps.INSTANCE);
    }

    public LevelStringReader.LevelResult consume(StringReader reader) throws CommandSyntaxException {
        final MutableObject<RegistryEntry<GameLevel>> mutableObject = new MutableObject<>();

        this.consume(reader, new LevelStringReader.Callbacks() {
            public void onLevel(RegistryEntry<GameLevel> item) {
                mutableObject.setValue(item);
            }
        });

        RegistryEntry<GameLevel> registryEntry = Objects.requireNonNull(mutableObject.getValue(), "Parser gave no item");
        return new LevelStringReader.LevelResult(registryEntry);
    }

    public void consume(StringReader reader, LevelStringReader.Callbacks callbacks) throws CommandSyntaxException {
        int i = reader.getCursor();

        try {
            new LevelStringReader.Reader(reader, callbacks).read();
        } catch (CommandSyntaxException var5) {
            reader.setCursor(i);
            throw var5;
        }
    }

    public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder) {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());
        LevelStringReader.SuggestionCallbacks suggestionCallbacks = new LevelStringReader.SuggestionCallbacks();
        LevelStringReader.Reader reader = new LevelStringReader.Reader(stringReader, suggestionCallbacks);

        try {
            reader.read();
        } catch (CommandSyntaxException ignored) {}

        return suggestionCallbacks.getSuggestions(builder, stringReader);
    }

    public interface Callbacks {
        default void onLevel(RegistryEntry<GameLevel> item) {}

        default void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {}
    }

    public record LevelResult(RegistryEntry<GameLevel> event) { }

    class Reader {
        private final StringReader reader;
        private final LevelStringReader.Callbacks callbacks;

        Reader(final StringReader reader, final LevelStringReader.Callbacks callbacks) {
            this.reader = reader;
            this.callbacks = callbacks;
        }

        public void read() throws CommandSyntaxException {
            this.callbacks.setSuggester(this::suggestLayers);
            this.readLayers();
            
            if (this.reader.canRead() && this.reader.peek() == '[') {
                this.callbacks.setSuggester(LevelStringReader.SUGGEST_DEFAULT);
            }
        }

        private void readLayers() throws CommandSyntaxException {
            int i = this.reader.getCursor();
            Identifier identifier = Identifier.fromCommandInput(this.reader);

            this.callbacks.onLevel(LevelStringReader.this.eventRegistry.getOptional(RegistryKey.of(RenegadeRegistries.levelKey, identifier)).orElseThrow(() -> {
                this.reader.setCursor(i);
                return LevelStringReader.INVALID_EVENT_ID_EXCEPTION.createWithContext(this.reader, identifier);
            }));
        }

        private CompletableFuture<Suggestions> suggestLayers(SuggestionsBuilder builder) {
            return CommandSource.suggestIdentifiers(LevelStringReader.this.eventRegistry.streamKeys().map(RegistryKey::getValue), builder);
        }
    }

    static class SuggestionCallbacks implements LevelStringReader.Callbacks {
        private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester = LevelStringReader.SUGGEST_DEFAULT;

        public void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {
            this.suggester = suggester;
        }

        public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder, StringReader reader) {
            return this.suggester.apply(builder.createOffset(reader.getCursor()));
        }
    }
}
