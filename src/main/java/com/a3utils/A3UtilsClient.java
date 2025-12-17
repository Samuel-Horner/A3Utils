package com.a3utils;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

public class A3UtilsClient implements ClientModInitializer {
    public static final Minecraft mc = Minecraft.getInstance();

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as
        // rendering.
    }
}