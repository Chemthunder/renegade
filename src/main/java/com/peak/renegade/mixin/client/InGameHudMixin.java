package com.peak.renegade.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.peak.renegade.core.RenegadeClient;
import com.peak.renegade.core.manager.RenegadeCrosshairFetcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {

    @Inject(method = "renderHealthBar", at = @At(value = "HEAD"), cancellable = true)
    private void renegade$noHealthbar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }

    @WrapOperation(
            method = "renderCrosshair",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void renegade$customCrosshairs(DrawContext instance, RenderPipeline pipeline, Identifier sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (RenegadeClient.isGameMember()) {
            if (MinecraftClient.getInstance().player != null) {
                original.call(instance, pipeline, RenegadeCrosshairFetcher.fetch(MinecraftClient.getInstance().player, sprite), x, y, width, height);
            }
        } else {
            original.call(instance, pipeline, sprite, x, y, width, height);
        }
    }

    @Inject(method = "renderFood", at = @At(value = "HEAD"), cancellable = true)
    private void renegade$fuckAppleSkin(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }
}
