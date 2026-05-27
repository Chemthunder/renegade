package com.peak.renegade.core.block.item;

import com.peak.renegade.core.index.RenegadeBlocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("deprecation")
public class GatewayBlockItem extends BlockItem {
    public GatewayBlockItem(Settings settings) {
        super(RenegadeBlocks.GATEWAY, settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("block.renegade.gateway.desc_0").formatted(Formatting.DARK_GRAY));
        textConsumer.accept(Text.translatable("block.renegade.gateway.desc_1").formatted(Formatting.DARK_GRAY));
    }
}
