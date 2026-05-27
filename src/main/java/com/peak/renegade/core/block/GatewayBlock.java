package com.peak.renegade.core.block;

import com.peak.renegade.api.utility.RenegadeInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class GatewayBlock extends Block {
    public static final BooleanProperty HAS_BEEN_USED = BooleanProperty.of("has_been_used");

    public GatewayBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(HAS_BEEN_USED, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_BEEN_USED);
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
        if (entity instanceof PlayerEntity player) {
            if (!state.get(HAS_BEEN_USED)) {
                RenegadeInstance instance = new RenegadeInstance(player);
                PlayerInstance p = instance.player();

                p.beginCountdown();
                world.setBlockState(pos, state.with(HAS_BEEN_USED, true));
            }
        }
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
