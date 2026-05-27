package com.peak.renegade.core.utility.command;

import com.peak.renegade.api.game.level.GameLevel;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author Chemthunder
 */
public class LevelArgument {
    private final RegistryEntry<GameLevel> event;

    public LevelArgument(RegistryEntry<GameLevel> event) {
        this.event = event;
    }

    public GameLevel getEvent() {
        return this.event.value();
    }

    private String getIdString() {
        return this.event.getKey().map(key -> key.getValue().toString()).orElseGet(() -> "unknown[" + this.event + "]");
    }
}
