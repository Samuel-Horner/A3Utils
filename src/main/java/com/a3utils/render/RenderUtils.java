package com.a3utils.render;

import net.minecraft.client.gui.GuiGraphics;

public class RenderUtils {
    public static void drawQuad(GuiGraphics context, int x, int y, int width, int height, int color) { context.fill(x, y, x + width, y + height, color); }
}
