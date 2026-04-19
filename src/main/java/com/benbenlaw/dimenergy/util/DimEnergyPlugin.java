package com.benbenlaw.dimenergy.util;

import com.benbenlaw.dimenergy.storage.DimEnergyStorage;
import edivad.dimstorage.api.AbstractDimStorage;
import edivad.dimstorage.api.DimStoragePlugin;
import edivad.dimstorage.api.Frequency;
import edivad.dimstorage.manager.DimStorageManager;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class DimEnergyPlugin implements DimStoragePlugin {
    @Override
    public AbstractDimStorage createDimStorage(DimStorageManager dimStorageManager, Frequency frequency) {
        return new DimEnergyStorage(dimStorageManager, frequency);
    }

    @Override
    public String identifier() {
        System.out.println("DimEnergyPlugin identifier() called");
        return "energy";
    }

    @Override
    public void sendClientInfo(Player player, List<AbstractDimStorage> list) {

    }
}
