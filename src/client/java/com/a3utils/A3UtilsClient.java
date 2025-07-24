package com.a3utils;

import com.a3utils.hud.DurabilityHUD;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;

public class A3UtilsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, DurabilityHUD.DURABILITY_HUD_LAYER, DurabilityHUD::render);
	}
}