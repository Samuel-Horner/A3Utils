package com.a3utils.render;

import net.minecraft.client.gui.DrawContext;

public class RenderUtils {
    public static void drawQuad(DrawContext context, int x, int y, int width, int height, int color) { context.fill(x, y, x + width, y + height, color); }
}
