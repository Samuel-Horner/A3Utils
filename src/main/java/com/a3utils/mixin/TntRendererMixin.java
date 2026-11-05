package com.a3utils.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.a3utils.hud.TntTimer;

import net.minecraft.client.renderer.entity.TntRenderer;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.world.entity.item.PrimedTnt;

@Mixin(TntRenderer.class)
public class TntRendererMixin {
    @Inject(method = "extractRenderState", at = @At(value = "HEAD"))
    public void extractRenderStateHead(final PrimedTnt entity, final TntRenderState state, final float partialTicks,
            CallbackInfo ci) {
        entity.setCustomName(TntTimer.getTntTimer((float) entity.getFuse()));
        entity.setCustomNameVisible(true);
    }
}
