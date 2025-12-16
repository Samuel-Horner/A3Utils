package com.a3utils.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.a3utils.A3UtilsClient;
import com.a3utils.event.ContainerTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

@Mixin(GuiGraphics.class)
public abstract class DrawContextMixin {

    @Inject(method = "setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V", at = @At("RETURN"))
    public void drawItemTooltip(Font textRenderer, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (stack.getComponents().has(DataComponents.CONTAINER)) { ContainerTooltip.renderTooltip((GuiGraphics) (Object) this, stack, x, y); }
    }
}