package com.spxctreofficial.lerpsneak.mixin.client.entity;

import com.spxctreofficial.lerpsneak.interfaces.IRenderViewEntity;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityLivingBase implements IRenderViewEntity {
    @Shadow public abstract void setSprinting(boolean sprinting);
    @Unique private float lerpSneak$lastCameraY;
    @Unique private float lerpSneak$cameraY;

    public MixinEntityPlayerSP(World worldIn) {
        super(worldIn);
    }

    @Override
    public float lerpSneak$getLastCameraY() {
        return lerpSneak$lastCameraY;
    }
    @Override
    public float lerpSneak$getCameraY() {
        return lerpSneak$cameraY;
    }
    @Override
    public void lerpSneak$updateCameraHeight(double tickDelta) {
        this.lerpSneak$lastCameraY = this.lerpSneak$getCameraY();
        lerpSneak$cameraY = (float) MathHelper.denormalizeClamp(this.lerpSneak$getLastCameraY(), getEyeHeight(), tickDelta / 2);
    }

}
