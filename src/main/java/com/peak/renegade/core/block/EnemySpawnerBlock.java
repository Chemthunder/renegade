package com.peak.renegade.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.renegade.core.block.entity.EnemySpawnerBlockEntity;
import com.peak.renegade.core.cca.core.WorldInstance;
import com.peak.renegade.core.client.screen.SpawnerDisplayScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.Random;

/**
 * @author Chemthunder
 */
public class EnemySpawnerBlock extends BlockWithEntity {
    public EnemySpawnerBlock(Settings settings) {
        super(settings);
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(EnemySpawnerBlock::new);
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.isCreative()) {
            if (world.getBlockEntity(pos) instanceof EnemySpawnerBlockEntity e) {
                if (world.isClient()) {
                    MinecraftClient.getInstance().setScreen(new SpawnerDisplayScreen(e));
                }
            }
        }

        return super.onUse(state, world, pos, player, hit);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnemySpawnerBlockEntity(pos, state);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getBlockEntity(pos) instanceof EnemySpawnerBlockEntity e) {
            if (e.getName().isEmpty()) {
                WorldInstance instance = WorldInstance.getInstance(world);

                e.setName(instance.getCurrentLayer().name() + "#" + instance.getCurrentLevel().name() + "#" + new Random().nextInt(90));
            }
        }
    }
}
