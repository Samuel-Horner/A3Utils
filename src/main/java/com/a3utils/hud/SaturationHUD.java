package com.a3utils.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class SaturationHUD {
    private static final float MAX_WIDTH = 81;

    public static void render(GuiGraphicsExtractor context, int top, int right, float saturation) {
        context.fill(right - (int) MAX_WIDTH, top + 9, right, top + 10, 0xFF000000);

        int left = right - (int) (MAX_WIDTH * (saturation / 20));
        context.fill(left, top + 9, right, top + 10, 0xFFFF0000);
    }
}
