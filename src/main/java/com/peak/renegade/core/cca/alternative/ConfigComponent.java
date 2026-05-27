package com.peak.renegade.core.cca.alternative;

import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.index.util.RenegadeColors;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class ConfigComponent implements AutoSyncedComponent {
    public static final ComponentKey<ConfigComponent> KEY = MiscUtils.getOrCreateKey(Renegade.id("config"), ConfigComponent.class);
    private final PlayerEntity player;

    public int weaponVariation1Start = RenegadeColors.VARIATION_ONE_START;
    public int weaponVariation1End = RenegadeColors.VARIATION_ONE_END;

    public int weaponVariation2Start = RenegadeColors.VARIATION_TWO_START;
    public int weaponVariation2End = RenegadeColors.VARIATION_TWO_END;

    public ConfigComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readData(ReadView readView) {
        this.weaponVariation1Start = readView.getInt("v1s", RenegadeColors.VARIATION_ONE_START);
        this.weaponVariation1End = readView.getInt("v1e", RenegadeColors.VARIATION_ONE_END);

        this.weaponVariation2Start = readView.getInt("v2s", RenegadeColors.VARIATION_TWO_START);
        this.weaponVariation2End = readView.getInt("v2e", RenegadeColors.VARIATION_TWO_END);
    }

    public void writeData(WriteView writeView) {
        writeView.putInt("v1s", this.weaponVariation1Start);
        writeView.putInt("v1e", this.weaponVariation1End);

        writeView.putInt("v2s", this.weaponVariation2Start);
        writeView.putInt("v2e", this.weaponVariation2End);
    }
}
