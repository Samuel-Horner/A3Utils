package com.a3utils.event;

import java.util.Iterator;
import java.util.stream.Stream;

import com.a3utils.A3UtilsClient;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;

public class ContainerTooltip {
    private static final Identifier TEXTURE_SHULKER_BOX = Identifier.ofVanilla("textures/gui/container/shulker_box.png");
    private static final int shulker_box_texture_width = 256;
    private static final int shulker_box_texture_height = 256;

    private static void renderInventoryBackground(DrawContext context, int x, int y, int color) {
        // https://github.com/sakura-ryoko/malilib/blob/1.21.8/src/main/java/fi/dy/masa/malilib/render/InventoryOverlay.java
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x      , y     , 0  , 0  , 7  , 61, shulker_box_texture_width, shulker_box_texture_height, color);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x + 7  , y     , 7  , 0  , 169, 7 , shulker_box_texture_width, shulker_box_texture_height, color);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x      , y + 61, 0  , 159, 169, 7 , shulker_box_texture_width, shulker_box_texture_height, color);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x + 169, y + 7 , 169, 105, 7  , 61, shulker_box_texture_width, shulker_box_texture_height, color);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE_SHULKER_BOX, x+ 7   , y + 7 , 7  , 17 , 162, 54, shulker_box_texture_width, shulker_box_texture_height, color);
    }

    private static int getBackgroundColorTint(ShulkerBoxBlock block) {
        final DyeColor dye = block.getColor() != null ? block.getColor() : DyeColor.PURPLE;
        return dye.getEntityColor();
    }

    private static void renderItemStacks(DrawContext context, ItemStack[] items, int x, int y) {
        for (int slot = 0; slot < 27; slot++) {
            final ItemStack item = items[slot];
            if (item == null) { continue; }
            
            final int item_x = x + (slot % 9) * 18 + 8;
            final int item_y = y + (slot / 9) * 18 + 8;
            context.drawItem(item, item_x, item_y);
            context.drawStackOverlay(A3UtilsClient.mc.textRenderer, item, item_x, item_y);
        }
    }

    public static void renderTooltip(DrawContext context, ItemStack stack, int mouseX, int mouseY) {
        if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
            ContainerComponent container = stack.getComponents().get(DataComponentTypes.CONTAINER);

            if (container == null) { return; }

            int item_count = (int) container.streamNonEmpty().count();
            if (item_count == 0) { return; }

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

            int x = MathHelper.clamp(mouseX + 8, 0, A3UtilsClient.mc.getWindow().getScaledWidth() - width);
            int y = MathHelper.clamp(mouseY - height - 18, 0, A3UtilsClient.mc.getWindow().getScaledHeight() - height);

            renderInventoryBackground(context, x, y, getBackgroundColorTint((ShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock()));
            renderItemStacks(context, items, x, y);

            // RenderUtils.drawQuad(context, x, y, 10, 10, 0xFFFF0000);
        }
    }
}
