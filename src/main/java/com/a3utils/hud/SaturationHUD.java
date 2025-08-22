package com.a3utils.hud;

import com.a3utils.A3Utils;
import com.a3utils.A3UtilsClient;
import com.a3utils.render.RenderUtils;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class SaturationHUD {
    private static final int WIDTH = 4;
    private static final int HEIGHT = 1;

    // private static final Identifier ICONS = Identifier.of(A3Utils.MOD_ID,
    // "icons.png");

    public static void render(DrawContext context, int top, int right, float saturation) {
        context.drawText(A3UtilsClient.mc.textRenderer, Float.toString(saturation), 0, 0, 0xFFFF0000, true);

        int i = 0;
        int left = right - WIDTH;
        final int adjusted_top = top + 9;
        while (i < saturation) {
            // if (i % 2 == 0) {
            // if (i == 0) {
            // RenderUtils.drawQuad(context, left, top, WIDTH, HEIGHT, 0xA0FFAA00);
            // } else {
            // RenderUtils.drawQuad(context, left, top, WIDTH - 1, HEIGHT, 0xA0FFAA00);
            // }
            // } else {
            // RenderUtils.drawQuad(context, left - 1, top, WIDTH + 1, HEIGHT, 0xA0FFAA00);
            // }

            if (i % 2 == 0) {
                RenderUtils.drawQuad(context, left, adjusted_top, WIDTH, HEIGHT, 0xFFFF0000);
            } else {
                RenderUtils.drawQuad(context, left - 1, adjusted_top, WIDTH + 1, HEIGHT, 0xFFFF0000);
            }

            left -= WIDTH;
            i += 1;
        }
    }
}
