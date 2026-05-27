package com.peak.renegade.mixin.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.peak.renegade.core.index.util.RenegadeColors;
import com.peak.renegade.core.manager.RenegadeContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = DrawContext.class)
public abstract class DrawContextMixin {
    @Shadow public abstract void fill(int x1, int y1, int x2, int y2, int color);

    @Shadow public abstract void fill(RenderPipeline pipeline, int x1, int y1, int x2, int y2, int color);

    @Inject(
            method = "drawStackOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawItemBar(Lnet/minecraft/item/ItemStack;II)V"
            )
    )
    private void renegade$colors(TextRenderer textRenderer, ItemStack stack, int x, int y, String stackCountText, CallbackInfo ci) {
        RenegadeContext.drawItemVariants((DrawContext) (Object) this, textRenderer, x, y, stack);
    }
}
