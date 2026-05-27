package com.peak.renegade.core.index;

import com.mojang.serialization.Codec;
import com.peak.renegade.core.Renegade;
import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

/**
 * @author Chemthunder
 */
public interface RenegadeComponentTypes {
    DataComponentTypeRegistrant COMPONENTS = new DataComponentTypeRegistrant(Renegade.MOD_ID);

    ComponentType<Integer> WEAPON_VARIATION = COMPONENTS.register(
            "weapon_variation",
            Codec.INT,
            PacketCodecs.INTEGER
    );

    static void init() {}
}
