package com.peak.renegade.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.renegade.core.index.util.RenegadeColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Chemthunder
 */
@Mixin(value = Item.class)
public abstract class ItemMixin {
    @WrapMethod(method = "getName(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/text/Text;")
    private Text renegade$allItemColors(ItemStack stack, Operation<Text> original) {
        return original.call(stack).copy().withColor(RenegadeColors.VARIATION_ONE_END);
    }

    @WrapMethod(method = "getName()Lnet/minecraft/text/Text;")
    private Text renegade$allItemColorsOverload(Operation<Text> original) {
        return original.call().copy().withColor(RenegadeColors.VARIATION_ONE_END);
    }
}