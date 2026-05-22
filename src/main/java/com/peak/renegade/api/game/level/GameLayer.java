package com.peak.renegade.api.game.level;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * @author Chemthunder
 */
public record GameLayer(String name, int color) {
    public static final Codec<GameLayer> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("name", "").forGetter(GameLayer::name),
                    Codec.INT.optionalFieldOf("color", 0).forGetter(GameLayer::color)
            ).apply(instance, GameLayer::new)
    );
}
