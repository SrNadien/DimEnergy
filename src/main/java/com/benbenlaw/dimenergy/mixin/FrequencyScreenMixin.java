package com.benbenlaw.dimenergy.mixin;

import com.benbenlaw.dimenergy.block.entity.BlockEntityDimEnergy;
import com.benbenlaw.dimenergy.network.UpdateDimEnergy;
import edivad.dimstorage.blockentity.BlockEntityFrequencyOwner;
import edivad.dimstorage.client.screen.pattern.FrequencyScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FrequencyScreen.class)
public abstract class FrequencyScreenMixin {

    @Shadow
    protected BlockEntityFrequencyOwner blockEntityFrequencyOwner;

    @Inject(method = "changeFrequency", at = @At("TAIL"))
    private void dimenergy$sendEnergyPacket(CallbackInfo ci) {
        if (this.blockEntityFrequencyOwner instanceof BlockEntityDimEnergy energy) {
            ClientPacketDistributor.sendToServer(new UpdateDimEnergy(energy));
        }
    }
}