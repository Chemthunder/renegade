package com.peak.renegade.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyReturnValue(method = "getEntityInteractionRange", at = @At(value = "RETURN"))
    private double renegade$lotzaLength(double original) {
        return original * 2;
    }
}
