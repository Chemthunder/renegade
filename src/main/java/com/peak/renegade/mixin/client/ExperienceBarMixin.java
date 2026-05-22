package com.peak.renegade.mixin.client;

import com.peak.renegade.core.RenegadeClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.ExperienceBar;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(ExperienceBar.class)
public abstract class ExperienceBarMixin {

    @Inject(method = "renderBar", at = @At(value = "HEAD"), cancellable = true)
    private void renegade$noXp(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAddons", at = @At(value = "HEAD"), cancellable = true)
    private void renegade$noXpLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }
}
