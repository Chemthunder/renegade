package com.peak.renegade.api.game.scene;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

/**
 * @author Chemthunder
 */
public enum GameScene implements StringIdentifiable {
    PRE("pre"),
    PLAYING("playing"),
    POST("post");

    private final String id;

    public static final Codec<GameScene> CODEC = StringIdentifiable.createCodec(GameScene::values);

    GameScene(String id) {
        this.id = id;
    }

    public String asString() {
        return this.id;
    }
}
