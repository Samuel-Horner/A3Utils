package com.a3utils.hud;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;

public class TntTimer {
    public static Component getTntTimer(float fuse) {
        fuse /= 20;
        TextColor color = TextColor.GREEN;
        if (fuse < 2) {
            color = TextColor.YELLOW;
        }
        if (fuse < 1) {
            color = TextColor.RED;
        }

        return Component.literal(String.format("%.2f", fuse)).withColor(color);        
    }
}
