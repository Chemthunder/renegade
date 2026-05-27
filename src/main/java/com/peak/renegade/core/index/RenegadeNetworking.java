package com.peak.renegade.core.index;

import com.peak.renegade.core.networking.c2s.BeginCountdownPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/**
 * @author Chemthunder
 */
public interface RenegadeNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playC2S().register(BeginCountdownPayload.ID, BeginCountdownPayload.CODEC);
    }

    static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(BeginCountdownPayload.ID, new BeginCountdownPayload.Receiver());
    }
}
