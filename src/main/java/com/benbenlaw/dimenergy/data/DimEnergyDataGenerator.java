package com.benbenlaw.dimenergy.data;

import com.benbenlaw.dimenergy.DimEnergy;
import com.benbenlaw.dimenergy.data.loot.DimEnergyBlockLoot;
import com.benbenlaw.dimenergy.data.loot.DimEnergyLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DimEnergy.MOD_ID)
public class DimEnergyDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new DimEnergyTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(DimEnergyBlockLoot::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(true, new DimEnergyModelProvider(packOutput));
        generator.addProvider(true, new DimEnergyRecipeProvider.Runner (packOutput, lookupProvider));


    }

}
