package com.a3utils.mixin;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class GameOptionsMixin {
    @Shadow
    OptionInstance<Double> gamma;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(Minecraft client, File file, CallbackInfo ci) {
        gamma.set(1.0);
    }
}
