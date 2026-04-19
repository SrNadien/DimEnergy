package com.benbenlaw.dimenergy.block;

import com.benbenlaw.dimenergy.DimEnergy;
import com.benbenlaw.dimenergy.block.entity.BlockEntityDimEnergy;
import com.benbenlaw.dimenergy.screen.DimEnergyMenu;
import edivad.dimstorage.blockentity.BlockEntityDimChest;
import edivad.dimstorage.blockentity.BlockEntityDimTank;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class DimEnergyCapabilities {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.Energy.BLOCK, DimEnergy.DIMENERGY_TILE.get(),
                BlockEntityDimEnergy::getEnergyHandler
        );
    }


}
