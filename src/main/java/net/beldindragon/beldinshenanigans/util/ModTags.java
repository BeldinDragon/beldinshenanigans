package net.beldindragon.beldinshenanigans.util;

import net.beldindragon.beldinshenanigans.Shenanigans;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> COLLECTION_BISMUTH = createTag("collection_bismuth");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Shenanigans.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> COLLECTION_BISMUTH = createTag("collection_bismuth");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Shenanigans.MOD_ID, name));
        }
    }
}
