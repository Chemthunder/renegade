package com.peak.renegade.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.renegade.core.Renegade;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = Window.class)
public abstract class WindowMixin {

    @WrapMethod(method = "setTitle")
    private void renegade$customWindowTitle(String title, Operation<Void> original) {
        original.call("Renegade # " + Renegade.getModData().getVersion());
    }
}
