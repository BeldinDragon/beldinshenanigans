package net.beldindragon.beldinshenanigans.datagen;

import net.beldindragon.beldinshenanigans.Shenanigans;
import net.beldindragon.beldinshenanigans.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Shenanigans.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());
        basicItem(ModItems.OBSIDIAN_STICK.get());

        basicItem(ModItems.CHISEL.get());
    }
}
