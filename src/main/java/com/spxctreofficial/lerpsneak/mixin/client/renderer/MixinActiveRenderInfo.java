package com.spxctreofficial.lerpsneak.mixin.client.renderer;


import com.spxctreofficial.lerpsneak.interfaces.IEntityRender;
import com.spxctreofficial.lerpsneak.interfaces.IRenderViewEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveRenderInfo.class)
public class MixinActiveRenderInfo {
    @Unique private static Entity lerpSneak$EntityPlayer;

    @ModifyArg(method = "updateRenderInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Vec3;<init>(DDD)V"), index = 1)
    private static double modifySetPosY(double y) {
        return MathHelper.denormalizeClamp(lerpSneak$EntityPlayer.prevPosY, lerpSneak$EntityPlayer.posY + MathHelper.denormalizeClamp(((IRenderViewEntity) lerpSneak$EntityPlayer).lerpSneak$getLastCameraY(), ((IRenderViewEntity) lerpSneak$EntityPlayer).lerpSneak$getCameraY(), ((IEntityRender) Minecraft.getMinecraft().entityRenderer).lerpSneak$getTickDelta()), ((IEntityRender) Minecraft.getMinecraft().entityRenderer).lerpSneak$getTickDelta());
    }

    @Inject(method = "updateRenderInfo", at = @At(value = "HEAD"), remap = false)
    private static void getPlayer(EntityPlayer entityplayerIn, boolean bl, CallbackInfo ci) {
        lerpSneak$EntityPlayer =  entityplayerIn;
    }
}
