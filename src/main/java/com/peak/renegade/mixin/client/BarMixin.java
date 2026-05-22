package com.peak.renegade.mixin.client;

import com.peak.renegade.core.RenegadeClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.Bar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = Bar.class)
public interface BarMixin {

    @Inject(method = "drawExperienceLevel", at = @At(value = "HEAD"), cancellable = true)
    private static void renegade$noXpLevelPLEASE(DrawContext context, TextRenderer textRenderer, int level, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }
}
