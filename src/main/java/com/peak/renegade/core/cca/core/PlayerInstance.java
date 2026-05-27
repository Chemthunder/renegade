package com.peak.renegade.core.cca.core;

import com.peak.renegade.api.game.scene.PlayerState;
import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class PlayerInstance implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<PlayerInstance> KEY = MiscUtils.getOrCreateKey(Renegade.id("player"), PlayerInstance.class);
    private final PlayerEntity player;

    private boolean active = false;

    private @Nullable PlayerState state = PlayerState.RUNNING;

    private int maxCountdown = (45 * 20);
    private int countdown = maxCountdown;

    public PlayerInstance(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void tick() {
        if (this.getState() == PlayerState.RUNNING) {
            if (this.countdown > 0) {
                this.countdown--;
                player.sendMessage(Text.literal(countdown + ":"), true);
                if ((this.countdown == 0) || (this.countdown % 20 == 0)) {
                    this.sync();
                }
            }
        }
    }

    public void readData(ReadView data) {
        this.active = data.getBoolean("Active", false);

        this.state = data.read("State", PlayerState.CODEC).orElse(PlayerState.RUNNING);

        this.maxCountdown = data.getInt("MaxCountdown", (45 * 20));
        this.countdown = data.getInt("Countdown", 0);
    }

    public void writeData(WriteView data) {
        data.putBoolean("Active", this.active);
        data.putInt("MaxCountdown", this.maxCountdown);
        data.putInt("Countdown", this.countdown);

        if (this.state != null) {
            data.put("State", PlayerState.CODEC, this.state);
        }
    }

    public static PlayerInstance getInstance(PlayerEntity player) {
        return KEY.get(player);
    }

    public boolean isDead() {
        return this.countdown == 0;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.sync();
    }

    public int getMaxCountdown() {
        return this.maxCountdown;
    }

    public void setMaxCountdown(int maxCountdown) {
        this.maxCountdown = maxCountdown;
        this.sync();
    }

    public int getCountdown() {
        return this.countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
        this.sync();
    }

    public void beginCountdown() {
        this.countdown = this.maxCountdown;
        this.sync();
    }

    public @Nullable PlayerState getState() {
        return this.state;
    }

    public void setState(@Nullable PlayerState state) {
        this.state = state;
        this.sync();
    }
}
