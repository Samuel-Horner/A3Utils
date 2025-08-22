package com.a3utils.mixin;

import net.minecraft.client.render.DimensionEffects.Nether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Nether.class)
public class DimensionEffectsNetherMixin {
    @Overwrite
    public boolean useThickFog(int camX, int camY) {
        return false;
    }
}
