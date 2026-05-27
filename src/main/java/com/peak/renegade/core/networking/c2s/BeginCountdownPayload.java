package com.peak.renegade.core.networking.c2s;

import com.peak.renegade.api.utility.RenegadeInstance;
import com.peak.renegade.core.Renegade;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

/**
 * @author Chemthunder
 */
public record BeginCountdownPayload() implements CustomPayload {
    public static final CustomPayload.Id<BeginCountdownPayload> ID = new CustomPayload.Id<>(Renegade.id("begin_countdown"));
    public static final PacketCodec<RegistryByteBuf, BeginCountdownPayload> CODEC = PacketCodec.unit(new BeginCountdownPayload());

    public static void send() {
        ClientPlayNetworking.send(new BeginCountdownPayload());
    }

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<BeginCountdownPayload> {
        public void receive(BeginCountdownPayload beginCountdownPayload, ServerPlayNetworking.Context context) {
            PlayerEntity player = context.player();
            RenegadeInstance instance = new RenegadeInstance(player);

            instance.player().setCountdown(instance.player().getMaxCountdown());
            Renegade.LOGGER.info("received BeginCountdownPayload");
        }
    }
}