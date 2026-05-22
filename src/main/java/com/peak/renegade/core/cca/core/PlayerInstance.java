package com.peak.renegade.core.cca.core;

import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class PlayerInstance implements AutoSyncedComponent {
    public static final ComponentKey<PlayerInstance> KEY = MiscUtils.getOrCreateKey(Renegade.id("player"), PlayerInstance.class);
    private final PlayerEntity player;

    private boolean active = false;

    public PlayerInstance(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readData(ReadView data) {
        this.active = data.getBoolean("Active", false);
    }

    public void writeData(WriteView data) {
        data.putBoolean("Active", active);
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.sync();
    }
}
