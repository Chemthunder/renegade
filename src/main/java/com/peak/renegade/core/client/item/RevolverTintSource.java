package com.peak.renegade.core.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.ColorHelper;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public record RevolverTintSource() implements TintSource {
    public static final MapCodec<RevolverTintSource> CODEC = MapCodec.unit(RevolverTintSource::new);

    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        return ColorHelper.withAlpha(1.0F, 0xFFFFFF);
    }

    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
