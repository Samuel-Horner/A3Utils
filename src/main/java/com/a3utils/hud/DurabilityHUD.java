package com.a3utils.hud;

import com.a3utils.A3Utils;
import com.a3utils.A3UtilsClient;
import com.a3utils.render.RenderUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

// Inspired by https://github.com/ReviversMC/microDurability.
public class DurabilityHUD {
    public static final Identifier DURABILITY_HUD_LAYER = Identifier.fromNamespaceAndPath(A3Utils.MOD_ID,
            "durability-hud-layer");

    private static final int equipment_slots[] = { EquipmentSlot.FEET.getIndex(36), EquipmentSlot.LEGS.getIndex(36),
            EquipmentSlot.CHEST.getIndex(36), EquipmentSlot.HEAD.getIndex(36) };

    private static void renderBar(GuiGraphics context, ItemStack stack, int x, int y) {
        if (stack == null || stack.isEmpty())
            return;
        if (!stack.isDamageableItem())
            return;

        RenderUtils.drawQuad(context, x, y - 1, 13, 2, 0xFF000000);
        RenderUtils.drawQuad(context, x, y - 1, stack.getBarWidth(), 1, ARGB.opaque(stack.getBarColor()));
    }

    private static void renderArmorBars(GuiGraphics context, int x, int y) {
        for (int slot : equipment_slots) {
            renderBar(context, A3UtilsClient.mc.player.getInventory().getItem(slot), x, y -= 3);
        }
    }

    private static void renderArmorArea(GuiGraphics context) {
        // https://github.com/ReviversMC/microDurability
        int scaledWidth = A3UtilsClient.mc.getWindow().getGuiScaledWidth();
        int scaledHeight = A3UtilsClient.mc.getWindow().getGuiScaledHeight();

        int x = (scaledWidth / 2) - 7;
        int y = scaledHeight - 28;
        if (A3UtilsClient.mc.player.experienceLevel > 0) {
            y -= 7;
        }

        renderArmorBars(context, x, y);
    }

    public static void render(GuiGraphics context) {
        renderArmorArea(context);
    }
}