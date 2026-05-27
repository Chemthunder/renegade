package com.peak.renegade.game.index;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.block.GatewayBlock;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author Chemthunder
 */
public interface GameBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Renegade.MOD_ID);

    Block GATEWAY = BLOCKS.register("gateway", GatewayBlock::new, AbstractBlock.Settings.copy(Blocks.BEDROCK).noCollision().noBlockBreakParticles());

    static void index() {}
}
