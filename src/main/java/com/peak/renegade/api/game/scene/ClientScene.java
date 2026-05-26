package com.peak.renegade.api.game.scene;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

/**
 * @author Chemthunder
 */
public enum ClientScene implements StringIdentifiable {
    NONE("none"),
    TITLE("title"),
    LEVEL_ANNOUNCE("level_announce"),
    PLAYING("playing");

    private final String id;

    public static final Codec<ClientScene> CODEC = StringIdentifiable.createCodec(ClientScene::values);

    ClientScene(String id) {
        this.id = id;
    }

    public String asString() {
        return this.id;
    }
}
