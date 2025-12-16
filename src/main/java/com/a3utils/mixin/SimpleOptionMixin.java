package com.a3utils.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionInstance.class)
public class SimpleOptionMixin<T> {
    @Shadow
    @Final
    Component caption;

    @Shadow
    T value;

    /**
     * Mixin to return the gamma value of this mod instead of the vanilla one
     */
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    public void getModValue(CallbackInfoReturnable<Double> info) {
        if (isGammaOption()) {
            if ((double)value >= 1.) { info.setReturnValue((double) 100.); }
        }
    }

    @Unique
    private boolean isGammaOption() {
        if (caption.getContents() instanceof TranslatableContents translatableTextContent) {
            return translatableTextContent.getKey().equals("options.gamma");
        }

        return false;
    }
}