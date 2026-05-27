package com.peak.renegade.core.cca.core;

import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.api.game.scene.ClientScene;
import com.peak.renegade.api.game.scene.GameScene;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.game.index.GameLayers;
import com.peak.renegade.game.index.GameLevels;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class WorldInstance implements AutoSyncedComponent {
    public static final ComponentKey<WorldInstance> KEY = MiscUtils.getOrCreateKey(Renegade.id("world"), WorldInstance.class);
    private final World world;

    private @Nullable GameScene currentScene = GameScene.PRE;

    private @Nullable GameLayer currentLayer = GameLayers.LOBBY;
    private @Nullable GameLevel currentLevel = GameLevels.LOBBY;

    public WorldInstance(World world) {
        this.world = world;
    }

    public void sync() {
        KEY.sync(this.world);
    }

    public void readData(ReadView data) {
        this.currentScene = data.read("CurrentScene", GameScene.CODEC).orElse(null);

        this.currentLayer = data.read("CurrentLayer", GameLayer.CODEC).orElse(null);
        this.currentLevel = data.read("CurrentLevel", GameLevel.CODEC).orElse(null);
    }

    public void writeData(WriteView data) {
        if (this.currentScene != null) {
            data.put("CurrentScene", GameScene.CODEC, this.currentScene);
        }

        if (this.currentLayer != null) {
            data.put("CurrentLayer", GameLayer.CODEC, this.currentLayer);
        }

        if (this.currentLevel != null) {
            data.put("CurrentLevel", GameLevel.CODEC, this.currentLevel);
        }
    }

    public static WorldInstance getInstance(World world) {
        return KEY.get(world);
    }

    public @Nullable GameScene getCurrentScene() {
        return this.currentScene;
    }

    public void setCurrentScene(@Nullable GameScene currentScene) {
        this.currentScene = currentScene;
        this.sync();
    }

    public @Nullable GameLayer getCurrentLayer() {
        return this.currentLayer;
    }

    public void setCurrentLayer(@Nullable GameLayer currentLayer) {
        this.currentLayer = currentLayer;
        this.sync();
    }

    public @Nullable GameLevel getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(@Nullable GameLevel currentLevel) {
        this.currentLevel = currentLevel;
        this.sync();
    }

    public void loadMap(PlayerEntity target, GameLayer layer, GameLevel level) {
        HudInstance client = HudInstance.getInstance(target);

        this.setCurrentLayer(layer);
        this.setCurrentLevel(level);

        client.setScene(ClientScene.LEVEL_ANNOUNCE);
        client.setTickStatus(true);
        client.setAge(0);
    }
}
