package com.a3utils.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Shadow
    SimpleOption<Double> gamma;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(MinecraftClient client, File file, CallbackInfo ci) {
        gamma.setValue(1.0);
    }
}
