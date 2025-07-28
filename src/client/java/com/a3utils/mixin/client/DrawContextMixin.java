package com.a3utils.mixin.client;

import net.minecraft.item.ItemStack;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.a3utils.event.ContainerTooltip;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {

    @Inject(method = "drawItemTooltip", at = @At("RETURN"))
    public void drawItemTooltip(TextRenderer textRenderer, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (stack.getComponents().contains(DataComponentTypes.CONTAINER)) { ContainerTooltip.renderTooltip((DrawContext) (Object) this, stack, x, y); }
    }
}