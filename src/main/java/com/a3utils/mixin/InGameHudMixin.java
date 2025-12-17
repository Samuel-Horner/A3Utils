package com.a3utils.mixin;

import com.a3utils.hud.DurabilityHUD;
import com.a3utils.hud.SaturationHUD;
import com.a3utils.hud.StatusEffectOverlayHUD;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderChat(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"))
    private void preRenderChat(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        DurabilityHUD.render(context);
    }

    @Inject(method = "renderFood", at = @At(value = "HEAD"))
    private void postRenderFood(GuiGraphics context, Player player, int top, int right, CallbackInfo ci) {
        SaturationHUD.render(context, top, right, player.getFoodData().getSaturationLevel());
    }

    // com.mojang.blaze3d.pipeline.RenderPipeline;
    // net.minecraft.util.Identifier
    // void net.minecraft.client.gui.DrawContext.drawGuiTexture(RenderPipeline
    // pipeline, Identifier sprite, int x, int y, int width, int height, int color)
    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V", shift = At.Shift.AFTER))
    private void postRenderIndividualStatusEffectOverlay(GuiGraphics context, DeltaTracker tickCounter,
            CallbackInfo ci, @Local MobEffectInstance statusEffectInstance, @Local(ordinal = 2) int k,
            @Local(ordinal = 3) int l) {
        // System.err.println("Test!");
        StatusEffectOverlayHUD.render(context, statusEffectInstance, k, l);
    }
}
