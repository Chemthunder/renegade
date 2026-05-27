package com.peak.renegade.core.client.screen;

import com.peak.renegade.core.Renegade;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public class RenegadeTitleScreen extends Screen {
    private final MinecraftClient client;

    public RenegadeTitleScreen() {
        super(Text.empty());
        this.client = MinecraftClient.getInstance();
    }

    protected void init() {
        Screen c = client.currentScreen;

        ButtonWidget openGame = ButtonWidget.builder(Text.literal("test"), button -> {

                })
                .tooltip(Tooltip.of(Text.literal("I'm a tooltip!")))
                .dimensions(c.width / 2, c.height / 2, 48, 16)
                .build();

        openGame.setPosition(c.width / 2, c.height / 2);

        this.addDrawable(openGame);
    }

    public void tick() {
        super.tick();
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
    }
}
