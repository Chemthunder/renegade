package com.peak.renegade.core.cca.core;

import com.peak.renegade.api.game.scene.ClientScene;
import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class HudInstance implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<HudInstance> KEY = MiscUtils.getOrCreateKey(Renegade.id("client"), HudInstance.class);
    private final PlayerEntity player;

    private boolean tickStatus = false;
    private int age = 0;

    private @Nullable ClientScene scene = ClientScene.NONE;

    public HudInstance(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (this.tickStatus) {
            this.age++;
        }
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readData(ReadView data) {
        this.tickStatus = data.getBoolean("TickStatus", false);
        this.age = data.getInt("Age", 0);

        this.scene = data.read("Scene", ClientScene.CODEC).orElse(null);
    }

    public void writeData(WriteView data) {
        data.putBoolean("TickStatus", this.tickStatus);
        data.putInt("Age", this.age);

        if (this.scene != null) {
            data.put("Scene", ClientScene.CODEC, this.scene);
        }
    }

    public static HudInstance getInstance(PlayerEntity player) {
        return KEY.get(player);
    }

    public void end() {
        this.setTickStatus(false);
        this.setAge(0);
        this.setScene(ClientScene.NONE);
    }

    public void end(ClientScene scene) {
        this.setTickStatus(false);
        this.setAge(0);
        this.setScene(scene);
    }

    public boolean canTick() {
        return this.tickStatus;
    }

    public void setTickStatus(boolean tickStatus) {
        this.tickStatus = tickStatus;
        this.sync();
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
        this.sync();
    }

    public @Nullable ClientScene getScene() {
        return this.scene;
    }

    public void setScene(@Nullable ClientScene scene) {
        this.scene = scene;
        this.sync();
    }
}
