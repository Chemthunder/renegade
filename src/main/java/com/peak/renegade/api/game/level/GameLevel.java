package com.peak.renegade.api.game.level;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * @author Chemthunder
 */
public record GameLevel(String name) {
    public static final Codec<GameLevel> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("name", "").forGetter(GameLevel::name)
            ).apply(instance, GameLevel::new)
    );
}
