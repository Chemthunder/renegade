package com.peak.renegade.core.index;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.block.entity.EnemySpawnerBlockEntity;
import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

/**
 * @author Chemthunder
 */
public interface RenegadeBlockEntityTypes {
    BlockEntityTypeRegistrant TYPES = new BlockEntityTypeRegistrant(Renegade.MOD_ID);

    BlockEntityType<EnemySpawnerBlockEntity> ENEMY_SPAWNER = TYPES.register("enemy_spawner",
            FabricBlockEntityTypeBuilder.create(
                    EnemySpawnerBlockEntity::new,
                    RenegadeBlocks.ENEMY_SPAWNER
            )
    );

    static void init() {}
}
