package com.benbenlaw.dimenergy.data;

import java.util.concurrent.CompletableFuture;

import com.benbenlaw.dimenergy.DimEnergy;
import edivad.dimstorage.setup.ModRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class DimEnergyRecipeProvider extends RecipeProvider {

  private final HolderLookup.RegistryLookup<Item> items;

  public DimEnergyRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
    super(registries, output);
    this.items = registries.lookupOrThrow(Registries.ITEM);
  }

  public static class Runner extends RecipeProvider.Runner {

    public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
      super(output, registries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
      return new DimEnergyRecipeProvider(registries, output);
    }

    @Override
    public String getName() {
      return "DimEnergyRecipeProvider";
    }
  }

  @Override
  protected void buildRecipes() {

    ShapedRecipeBuilder.shaped(this.items, RecipeCategory.MISC, DimEnergy.DIMENERGY.get())
            .pattern("aba")
            .pattern("bcb")
            .pattern("ada")
            .define('a', ModRegistration.DIMWALL)
            .define('b', Items.REDSTONE)
            .define('c', Items.REDSTONE_BLOCK)
            .define('d', ModRegistration.SOLIDDIMCORE)
            .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
            .save(output);
  }
}