package com.peak.renegade.core.utility.command;

import com.llamalad7.mixinextras.lib.apache.commons.mutable.MutableObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.DynamicOps;
import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.core.index.RenegadeRegistries;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.DimensionArgumentType;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
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
public class LayerStringReader {
    public static final DynamicCommandExceptionType INVALID_EVENT_ID_EXCEPTION = new DynamicCommandExceptionType(
            id -> Text.stringifiedTranslatable("argument.ethos_event.id.invalid", id));

    public static final Function<SuggestionsBuilder, CompletableFuture<Suggestions>> SUGGEST_DEFAULT = SuggestionsBuilder::buildFuture;

    public final RegistryWrapper.Impl<GameLayer> eventRegistry;
    public final DynamicOps<NbtElement> nbtOps;

    public LayerStringReader(RegistryWrapper.WrapperLookup registryLookup) {
        this.eventRegistry = registryLookup.getOrThrow(RenegadeRegistries.layerKey);
        this.nbtOps = registryLookup.getOps(NbtOps.INSTANCE);
    }

    public LayerStringReader.LayerResult consume(StringReader reader) throws CommandSyntaxException {
        final MutableObject<RegistryEntry<GameLayer>> mutableObject = new MutableObject<>();

        this.consume(reader, new LayerStringReader.Callbacks() {
            public void onLayer(RegistryEntry<GameLayer> item) {
                mutableObject.setValue(item);
            }
        });



        RegistryEntry<GameLayer> registryEntry = Objects.requireNonNull(mutableObject.getValue(), "Parser gave no item");
        return new LayerStringReader.LayerResult(registryEntry);
    }

    public void consume(StringReader reader, LayerStringReader.Callbacks callbacks) throws CommandSyntaxException {
        int i = reader.getCursor();

        try {
            new LayerStringReader.Reader(reader, callbacks).read();
        } catch (CommandSyntaxException var5) {
            reader.setCursor(i);
            throw var5;
        }

        //
    }

    public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder) {
        StringReader stringReader = new StringReader(builder.getInput());
        stringReader.setCursor(builder.getStart());
        LayerStringReader.SuggestionCallbacks suggestionCallbacks = new LayerStringReader.SuggestionCallbacks();
        LayerStringReader.Reader reader = new LayerStringReader.Reader(stringReader, suggestionCallbacks);

        try {
            reader.read();
        } catch (CommandSyntaxException ignored) {}

        return suggestionCallbacks.getSuggestions(builder, stringReader);
    }

    public interface Callbacks {
        default void onLayer(RegistryEntry<GameLayer> item) {}

        default void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {}
    }

    public record LayerResult(RegistryEntry<GameLayer> event) { }

    class Reader {
        private final StringReader reader;
        private final LayerStringReader.Callbacks callbacks;

        Reader(final StringReader reader, final LayerStringReader.Callbacks callbacks) {
            this.reader = reader;
            this.callbacks = callbacks;
        }

        public void read() throws CommandSyntaxException {
            this.callbacks.setSuggester(this::suggestLayers);
            this.readLayers();
            
            if (this.reader.canRead() && this.reader.peek() == '[') {
                this.callbacks.setSuggester(LayerStringReader.SUGGEST_DEFAULT);
            }
        }

        private void readLayers() throws CommandSyntaxException {
            int i = this.reader.getCursor();
            Identifier identifier = Identifier.fromCommandInput(this.reader);

            this.callbacks.onLayer(LayerStringReader.this.eventRegistry.getOptional(RegistryKey.of(RenegadeRegistries.layerKey, identifier)).orElseThrow(() -> {
                this.reader.setCursor(i);
                return LayerStringReader.INVALID_EVENT_ID_EXCEPTION.createWithContext(this.reader, identifier);
            }));
        }

        private CompletableFuture<Suggestions> suggestLayers(SuggestionsBuilder builder) {
            return CommandSource.suggestIdentifiers(LayerStringReader.this.eventRegistry.streamKeys().map(RegistryKey::getValue), builder);
        }
    }

    static class SuggestionCallbacks implements LayerStringReader.Callbacks {
        private Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester = LayerStringReader.SUGGEST_DEFAULT;

        public void setSuggester(Function<SuggestionsBuilder, CompletableFuture<Suggestions>> suggester) {
            this.suggester = suggester;
        }

        public CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder, StringReader reader) {
            return this.suggester.apply(builder.createOffset(reader.getCursor()));
        }
    }
}
