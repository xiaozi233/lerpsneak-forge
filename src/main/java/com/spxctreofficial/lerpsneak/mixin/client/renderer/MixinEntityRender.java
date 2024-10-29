package com.spxctreofficial.lerpsneak.mixin.client.renderer;

import com.spxctreofficial.lerpsneak.interfaces.IEntityRender;
import com.spxctreofficial.lerpsneak.interfaces.IRenderViewEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRender implements IEntityRender {
    @Unique
    private float lerpSneak$tickDelta;

    @Shadow @Final private Minecraft mc;
    @Inject(method = "orientCamera", at = @At(value = "HEAD"))
    private void getTickDelta(float tickDelta, CallbackInfo ci) {
        this.lerpSneak$tickDelta = tickDelta;
    }

    @Redirect(method = "orientCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getEyeHeight()F"))
    private float lerpSneak(Entity entity) {
        return (float) MathHelper.clampedLerp(((IRenderViewEntity) entity).lerpSneak$getLastCameraY(), ((IRenderViewEntity) entity).lerpSneak$getCameraY(), lerpSneak$tickDelta);
    }

    @Inject(method = "updateRenderer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getLightBrightness(Lnet/minecraft/util/math/BlockPos;)F"))
    private void updateCameraHeight(CallbackInfo ci) {
        if (this.mc.getRenderViewEntity() != null) {
            ((IRenderViewEntity) this.mc.getRenderViewEntity()).lerpSneak$updateCameraHeight(lerpSneak$tickDelta);
        }
    }

    @Override
    public float lerpSneak$getTickDelta() {
        return lerpSneak$tickDelta;
    }
}
