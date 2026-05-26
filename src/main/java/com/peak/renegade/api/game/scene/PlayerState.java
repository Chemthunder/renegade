package com.peak.renegade.api.game.scene;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

/**
 * @author Chemthunder
 */
public enum PlayerState implements StringIdentifiable {
    LOCKED("locked"),
    RUNNING("running");

    private final String id;

    public static final Codec<PlayerState> CODEC = StringIdentifiable.createCodec(PlayerState::values);

    PlayerState(String id) {
        this.id = id;
    }

    public String asString() {
        return "";
    }
}
