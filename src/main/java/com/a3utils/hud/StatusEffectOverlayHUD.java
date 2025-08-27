package com.a3utils.hud;

import com.a3utils.A3UtilsClient;
import com.a3utils.render.RenderUtils;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;

public class StatusEffectOverlayHUD {
    private static String getDuration(StatusEffectInstance instance) {
        if (instance.isInfinite()) return "";

        int duration = instance.getDuration();
        int minutes = duration / 1200;

        if (minutes >= 10) { return "9:99"; }
        
        return Integer.toString(minutes) + ":" + Integer.toString((duration / 20) % 60);
    }

    public static void render(DrawContext context, StatusEffectInstance instance, int left, int top) {
        context.drawText(A3UtilsClient.mc.textRenderer, getDuration(instance), left + 2, top + 14, 0xFFFFFFFF, true);
    }
}
