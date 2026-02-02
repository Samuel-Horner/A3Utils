package com.a3utils.hud;

import com.a3utils.A3UtilsClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;

public class StatusEffectOverlayHUD {
    private static int getColor(MobEffectInstance instance) {
        if (instance.getDuration() <= 200) {
            return 0xFFFF0000;
        }

        return 0xFFFFFFFF;
    }

    private static String getDuration(MobEffectInstance instance) {
        if (instance.isInfiniteDuration())
            return "";

        int duration = instance.getDuration();
        int minutes = duration / 1200;

        if (minutes >= 10) {
            return "9:99";
        }

        int seconds = (duration / 20) % 60;
        if (seconds <= 9) {
            return Integer.toString(minutes) + ":0" + Integer.toString((duration / 20) % 60);
        } else {
            return Integer.toString(minutes) + ":" + Integer.toString((duration / 20) % 60);
        }

    }

    public static void render(GuiGraphics context, MobEffectInstance instance, int left, int top) {
        context.drawString(A3UtilsClient.mc.font, getDuration(instance), left + 2, top + 14, getColor(instance), true);
    }
}
