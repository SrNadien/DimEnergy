package com.benbenlaw.dimenergy.screen;

import com.benbenlaw.dimenergy.DimEnergy;
import com.benbenlaw.dimenergy.block.entity.BlockEntityDimEnergy;
import edivad.dimstorage.menu.DimStorageMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

public class DimEnergyMenu extends DimStorageMenu {
    public BlockEntityDimEnergy owner;
    public boolean isOpen;

    public DimEnergyMenu(int windowId, Inventory inventory, BlockEntityDimEnergy owner, boolean isOpen) {
        super((MenuType) DimEnergy.DIMENERGY_MENU.get(), windowId);
        this.owner = owner;
        this.isOpen = isOpen;
        this.addInventorySlots(inventory);

        this.broadcastChanges();
    }

    public boolean stillValid(Player player) {
        return true;
    }

    public void removed(Player player) {
        super.removed(player);
    }
}
