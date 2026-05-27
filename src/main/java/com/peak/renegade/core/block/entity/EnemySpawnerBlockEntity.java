package com.peak.renegade.core.block.entity;

import com.peak.renegade.core.index.RenegadeBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtList;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Chemthunder
 */
public class EnemySpawnerBlockEntity extends BlockEntity {
    private List<UUID> entities = new ArrayList<>();
    private String name = "";

    private int enemiesToSpawn = 0;

    public EnemySpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(RenegadeBlockEntityTypes.ENEMY_SPAWNER, pos, state);
    }

    public void tick(BlockPos pos, BlockState state, EnemySpawnerBlockEntity entity) {

    }

    public void spawnEntities(BlockPos pos, BlockState state, EnemySpawnerBlockEntity entity) {

    }

    protected void readData(ReadView view) {
        this.enemiesToSpawn = view.getInt("EnemiesToSpawn", 0);
        this.name = view.getString("Name", "");
    }

    protected void writeData(WriteView view) {
        view.putInt("EnemiesToSpawn", this.enemiesToSpawn);
        view.putString("Name", this.name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String s) {
        this.name = s;
        this.markDirty();
    }

    public int getEnemiesToSpawn() {
        return this.enemiesToSpawn;
    }
}
