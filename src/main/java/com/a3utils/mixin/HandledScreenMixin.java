package com.a3utils.mixin;

import com.a3utils.event.ContainerTooltip;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {
    @Shadow
    @Nullable
    protected Slot focusedSlot;
    /*
     * protected void drawMouseoverTooltip(DrawContext context, int x, int y) {
     * if (this.focusedSlot != null && this.focusedSlot.hasStack()) {
     * ItemStack itemStack = this.focusedSlot.getStack();
     * if (this.handler.getCursorStack().isEmpty() ||
     * this.isItemTooltipSticky(itemStack)) {
     * context.drawTooltip(this.textRenderer, this.getTooltipFromItem(itemStack),
     * itemStack.getTooltipData(), x, y,
     * (Identifier)itemStack.get(DataComponentTypes.TOOLTIP_STYLE));
     * }
     * 
     * }
     * }
     */

    @Inject(method = "drawMouseoverTooltip", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/gui/DrawContext;drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/util/Identifier;)V"))
    private void onRenderTooltip(DrawContext drawContext, int x, int y, CallbackInfo ci) {
        ItemStack stack = this.focusedSlot.getStack();
        if (stack.getComponents().contains(DataComponentTypes.CONTAINER)) {
            ContainerTooltip.renderTooltip(drawContext, stack, x, y);
        }
    }
}