package com.a3utils.hud;

import net.minecraft.client.gui.DrawContext;

public class SaturationHUD {
    private static final float MAX_WIDTH = 81; 

    public static void render(DrawContext context, int top, int right, float saturation) {
        int left = right - (int) (MAX_WIDTH * (saturation / 20));
        context.fill(left, top + 9, right, top + 10, 0xFFFF0000);
    }
}
