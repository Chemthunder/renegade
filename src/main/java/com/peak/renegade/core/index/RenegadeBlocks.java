package com.peak.renegade.core.index;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.block.EnemySpawnerBlock;
import com.peak.renegade.core.block.GatewayBlock;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author Chemthunder
 */
public interface RenegadeBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Renegade.MOD_ID);

    Block GATEWAY = BLOCKS.register("gateway", GatewayBlock::new, AbstractBlock.Settings.copy(Blocks.BEDROCK).noCollision().noBlockBreakParticles());
    Block ENEMY_SPAWNER = BLOCKS.registerWithItem("enemy_spawner", EnemySpawnerBlock::new, AbstractBlock.Settings.copy(Blocks.BEDROCK).noCollision().noBlockBreakParticles());

    static void init() {}
}
