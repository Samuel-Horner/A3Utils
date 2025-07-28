package com.a3utils.hud;

import com.a3utils.A3Utils;
import com.a3utils.A3UtilsClient;
import com.a3utils.render.RenderUtils;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

// Inspired by https://github.com/ReviversMC/microDurability.
public class DurabilityHUD {
    public static final Identifier DURABILITY_HUD_LAYER = Identifier.of(A3Utils.MOD_ID, "durability-hud-layer");

    private static final int equipment_slots[] = {EquipmentSlot.FEET.getOffsetEntitySlotId(36), EquipmentSlot.LEGS.getOffsetEntitySlotId(36), EquipmentSlot.CHEST.getOffsetEntitySlotId(36), EquipmentSlot.HEAD.getOffsetEntitySlotId(36)};

    private static void renderBar(DrawContext context, ItemStack stack, int x, int y) {
        if (stack == null || stack.isEmpty()) return;
        if (!stack.isDamageable()) return;

        RenderUtils.drawQuad(context, x, y, 13, 2, 0xFF000000);
        RenderUtils.drawQuad(context, x, y - 1, stack.getItemBarStep(), 1, ColorHelper.fullAlpha(stack.getItemBarColor()));
    }

    private static void renderArmorBars(DrawContext context, int x, int y) {
        for (int slot : equipment_slots) { renderBar(context, A3UtilsClient.mc.player.getInventory().getStack(slot), x, y -= 3); }
    }

    private static void renderArmorArea(DrawContext context) {
        // https://github.com/ReviversMC/microDurability
        int scaledWidth = A3UtilsClient.mc.getWindow().getScaledWidth();
        int scaledHeight = A3UtilsClient.mc.getWindow().getScaledHeight();

        int x = (scaledWidth / 2) - 7;
        int y = scaledHeight - 27;
        if (A3UtilsClient.mc.player.experienceLevel > 0) { y -= 7; }

        renderArmorBars(context, x, y);
    }

    public static void render(DrawContext context) {
        renderArmorArea(context);
    }
}