package com.a3utils.event;

import java.util.Iterator;
import java.util.stream.Stream;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import com.a3utils.A3UtilsClient;

public class ContainerTooltip {
    private static final Identifier TEXTURE_SHULKER_BOX = Identifier
            .withDefaultNamespace("textures/gui/container/shulker_box.png");
    private static final int shulker_box_texture_width = 256;
    private static final int shulker_box_texture_height = 256;

    private static void renderInventoryBackground(GuiGraphics context, int x, int y, int color) {
        // https://github.com/sakura-ryoko/malilib/blob/1.21.8/src/main/java/fi/dy/masa/malilib/render/InventoryOverlay.java
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x, y, 0, 0, 7, 61, shulker_box_texture_width,
                shulker_box_texture_height, color);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x + 7, y, 7, 0, 169, 7,
                shulker_box_texture_width, shulker_box_texture_height, color);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x, y + 61, 0, 159, 169, 7,
                shulker_box_texture_width, shulker_box_texture_height, color);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x + 169, y + 7, 169, 105, 7, 61,
                shulker_box_texture_width, shulker_box_texture_height, color);
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x + 7, y + 7, 7, 17, 162, 54,
                shulker_box_texture_width, shulker_box_texture_height, color);
    }

    private static int getBackgroundColorTint(ShulkerBoxBlock block) {
        final DyeColor dye = block.getColor() != null ? block.getColor() : DyeColor.PURPLE;
        return dye.getTextureDiffuseColor();
    }

    private static void renderItemStacks(GuiGraphics context, ItemStack[] items, int x, int y) {
        for (int slot = 0; slot < 27; slot++) {
            final ItemStack item = items[slot];
            if (item == null) {
                continue;
            }

            final int item_x = x + (slot % 9) * 18 + 8;
            final int item_y = y + (slot / 9) * 18 + 8;
            context.renderItem(item, item_x, item_y);
            context.renderItemDecorations(A3UtilsClient.mc.font, item, item_x, item_y);
        }
    }

    public static void renderTooltip(GuiGraphics context, ItemStack stack, int mouseX, int mouseY) {
        if (stack.getItem() instanceof BlockItem
                && ((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
            ItemContainerContents container = stack.getComponents().get(DataComponents.CONTAINER);

            if (container == null) {
                return;
            }

            int item_count = (int) container.nonEmptyStream().count();
            if (item_count == 0) {
                return;
            }

            Stream<ItemStack> container_stream = container.stream();
            ItemStack[] items = new ItemStack[27];
            Iterator<ItemStack> container_iterator = container_stream.iterator();

            int slot_iterator = 0;
            while (container_iterator.hasNext()) {
                items[slot_iterator] = container_iterator.next().copy();
                slot_iterator++;
            }

            final int width = 176;
            final int height = 68;

            int x = Mth.clamp(mouseX + 8, 0, A3UtilsClient.mc.getWindow().getGuiScaledWidth() - width);
            int y = Mth.clamp(mouseY - height - 18, 0, A3UtilsClient.mc.getWindow().getGuiScaledHeight() - height);

            renderInventoryBackground(context, x, y,
                    getBackgroundColorTint((ShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock()));
            renderItemStacks(context, items, x, y);

            // RenderUtils.drawQuad(context, x, y, 10, 10, 0xFFFF0000);
        }
    }
}
