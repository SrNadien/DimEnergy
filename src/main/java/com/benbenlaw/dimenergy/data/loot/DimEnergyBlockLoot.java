package com.benbenlaw.dimenergy.data.loot;

import java.util.List;
import java.util.Set;

import com.benbenlaw.dimenergy.DimEnergy;
import edivad.dimstorage.items.components.DimStorageComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class DimEnergyBlockLoot extends BlockLootSubProvider {

  public DimEnergyBlockLoot(HolderLookup.Provider registries) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
  }

  @Override
  protected void generate() {
    this.add(DimEnergy.DIMENERGY.get(), DimEnergyBlockLoot::createStandardTable);
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return List.of(DimEnergy.DIMENERGY.get());
  }

  private static LootTable.Builder createStandardTable(Block block) {
    var builder = LootPool.lootPool()
        .setRolls(ConstantValue.exactly(1))
        .add(LootItem.lootTableItem(block)
            .apply(CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                .include(DimStorageComponents.FREQUENCY.get())
                .include(DataComponents.CUSTOM_NAME))
        ).when(ExplosionCondition.survivesExplosion());
    return LootTable.lootTable().withPool(builder);
  }
}