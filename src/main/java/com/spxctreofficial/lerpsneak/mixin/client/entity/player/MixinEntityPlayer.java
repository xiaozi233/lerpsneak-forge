package com.spxctreofficial.lerpsneak.mixin.client.entity.player;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer{
    @ModifyVariable(method = "getEyeHeight", at = @At(value = "STORE", ordinal = 2), ordinal = 0)
    private float setSneakHeight(float f) {
        return 1.27f;
    } // set the client-side sneak height to 1.14+
}
