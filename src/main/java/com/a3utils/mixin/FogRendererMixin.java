package com.a3utils.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = FogRenderer.class)
public class FogRendererMixin {

    @Shadow
    @Final
    private static List<FogEnvironment> FOG_ENVIRONMENTS;

    @Inject(method = "setupFog", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/fog/FogData;renderDistanceEnd:F", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void postFogSetup(Camera camera, int renderDistance, DeltaTracker deltaTracker, float f, ClientLevel level, CallbackInfoReturnable<Vector4f> cir, float g, Vector4f vector4f, float h, FogType fogType, Entity entity, FogData fogData) {
        if (fogType == FogType.ATMOSPHERIC) {
            if (!FOG_ENVIRONMENTS.get(2).isApplicable(fogType, entity) && !FOG_ENVIRONMENTS.get(3).isApplicable(fogType, entity)) {
                fogData.environmentalStart = Float.MAX_VALUE;
                fogData.environmentalEnd = Float.MAX_VALUE;
            }
        }
    }
}