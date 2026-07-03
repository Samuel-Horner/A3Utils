package com.a3utils.mixin;

import com.a3utils.hud.DurabilityHUD;
import com.a3utils.hud.SaturationHUD;
import com.a3utils.hud.StatusEffectOverlayHUD;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class HudMixin {
    // @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;extractChat(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"))
    @Inject(method = "extractChat", at = @At(value = "HEAD"))
    private void preRenderChat(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        DurabilityHUD.render(context);
    }

    @Inject(method = "extractFood", at = @At(value = "HEAD"))
    private void preRenderFood(final GuiGraphicsExtractor graphics, final Player player, final int yLineBase, final int xRight, CallbackInfo ci) {
        SaturationHUD.render(graphics, yLineBase, xRight, player.getFoodData().getSaturationLevel());
    }

    // com.mojang.blaze3d.pipeline.RenderPipeline;
    // net.minecraft.util.Identifier
    // void net.minecraft.client.gui.DrawContext.drawGuiTexture(RenderPipeline
    // pipeline, Identifier sprite, int x, int y, int width, int height, int color)
    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V", shift = At.Shift.AFTER))
    private void postRenderIndividualStatusEffectOverlay(GuiGraphicsExtractor context, DeltaTracker tickCounter,
            CallbackInfo ci, @Local MobEffectInstance statusEffectInstance, @Local(ordinal = 2) int k,
            @Local(ordinal = 3) int l) {
        // System.err.println("Test!");
        StatusEffectOverlayHUD.render(context, statusEffectInstance, k, l);
    }
}
