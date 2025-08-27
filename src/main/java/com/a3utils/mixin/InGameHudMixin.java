package com.a3utils.mixin;

import com.a3utils.hud.DurabilityHUD;
import com.a3utils.hud.SaturationHUD;
import com.a3utils.hud.StatusEffectOverlayHUD;
import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderChat(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V"))
    private void preRenderChat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        DurabilityHUD.render(context);
    }

    @Inject(method = "renderFood", at = @At(value = "HEAD"))
    private void postRenderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        SaturationHUD.render(context, top, right, player.getHungerManager().getSaturationLevel());
    }

    // com.mojang.blaze3d.pipeline.RenderPipeline;
    // net.minecraft.util.Identifier
    // void net.minecraft.client.gui.DrawContext.drawGuiTexture(RenderPipeline pipeline, Identifier sprite, int x, int y, int width, int height, int color)
    @Inject(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIII)V", shift = At.Shift.AFTER))
    private void postRenderIndividualStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci, @Local StatusEffectInstance statusEffectInstance, @Local(ordinal = 2) int k, @Local(ordinal = 3) int l) {
        // System.err.println("Test!");
        StatusEffectOverlayHUD.render(context, statusEffectInstance, k, l);
    }
}
