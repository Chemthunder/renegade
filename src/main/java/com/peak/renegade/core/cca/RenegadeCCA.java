package com.peak.renegade.core.cca;

import com.peak.renegade.core.cca.core.HudInstance;
import com.peak.renegade.core.cca.core.PlayerInstance;
import com.peak.renegade.core.cca.core.WorldInstance;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

/**
 * @author Chemthunder
 */
public class RenegadeCCA implements EntityComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(
                PlayerInstance.KEY,
                PlayerInstance::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );

        registry.registerForPlayers(
                HudInstance.KEY,
                HudInstance::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(
                WorldInstance.KEY,
                WorldInstance::new
        );
    }
}
