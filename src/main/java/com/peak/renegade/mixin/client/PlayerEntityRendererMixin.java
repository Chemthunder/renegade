package com.peak.renegade.mixin.client;

import com.peak.renegade.core.RenegadeClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "renderArm", at = @At(value = "HEAD"), cancellable = true)
    private void renegade$noARm(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, Identifier skinTexture, ModelPart arm, boolean sleeveVisible, CallbackInfo ci) {
        if (RenegadeClient.isGameMember()) {
            ci.cancel();
        }
    }
}
