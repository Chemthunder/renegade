package com.peak.renegade.core.client.screen;

import com.peak.renegade.core.block.entity.EnemySpawnerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * @author Chemthunder
 */
public class SpawnerDisplayScreen extends Screen {
    private final MinecraftClient client;
    private final EnemySpawnerBlockEntity blockEntity;

    public SpawnerDisplayScreen(EnemySpawnerBlockEntity entity) {
        super(Text.empty());
        this.client = MinecraftClient.getInstance();
        this.blockEntity = entity;
    }

    protected void init() {

    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.drawTooltip(
                List.of(
                        Text.of(this.blockEntity.getName()).asOrderedText(),
                        Text.translatable("block.renegade.enemy_spawner.enemies", this.blockEntity.getEnemiesToSpawn()).asOrderedText()
                ),
                mouseX,
                mouseY
        );

        super.render(context, mouseX, mouseY, deltaTicks);
    }
}
