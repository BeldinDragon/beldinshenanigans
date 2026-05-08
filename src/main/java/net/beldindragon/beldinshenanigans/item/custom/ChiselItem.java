package net.beldindragon.beldinshenanigans.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class ChiselItem extends Item {
    public static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    // === Stone family ===
                    Map.entry(Blocks.STONE,                          Blocks.STONE_BRICKS),
                    Map.entry(Blocks.STONE_BRICKS,                   Blocks.CHISELED_STONE_BRICKS),
                    Map.entry(Blocks.CHISELED_STONE_BRICKS,          Blocks.CRACKED_STONE_BRICKS),
                    Map.entry(Blocks.CRACKED_STONE_BRICKS,           Blocks.STONE),

                    // === Deepslate family ===
                    Map.entry(Blocks.COBBLED_DEEPSLATE,              Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE,                      Blocks.DEEPSLATE_BRICKS),
                    Map.entry(Blocks.DEEPSLATE_BRICKS,               Blocks.CHISELED_DEEPSLATE),
                    Map.entry(Blocks.CHISELED_DEEPSLATE,             Blocks.POLISHED_DEEPSLATE),
                    Map.entry(Blocks.POLISHED_DEEPSLATE,             Blocks.COBBLED_DEEPSLATE),

                    // === End Stone family ===
                    Map.entry(Blocks.END_STONE,                      Blocks.END_STONE_BRICKS),
                    Map.entry(Blocks.END_STONE_BRICKS,               Blocks.END_STONE),

                    // === Nether family ===
                    Map.entry(Blocks.BLACKSTONE,                     Blocks.POLISHED_BLACKSTONE),
                    Map.entry(Blocks.POLISHED_BLACKSTONE,            Blocks.POLISHED_BLACKSTONE_BRICKS),
                    Map.entry(Blocks.POLISHED_BLACKSTONE_BRICKS,     Blocks.CHISELED_POLISHED_BLACKSTONE),
                    Map.entry(Blocks.CHISELED_POLISHED_BLACKSTONE,   Blocks.BLACKSTONE),
                    Map.entry(Blocks.NETHER_BRICKS,                  Blocks.CHISELED_NETHER_BRICKS),
                    Map.entry(Blocks.CHISELED_NETHER_BRICKS,         Blocks.CRACKED_NETHER_BRICKS),
                    Map.entry(Blocks.CRACKED_NETHER_BRICKS,          Blocks.NETHER_BRICKS),

                    // === Sandstone family ===
                    Map.entry(Blocks.SANDSTONE,                      Blocks.CHISELED_SANDSTONE),
                    Map.entry(Blocks.CHISELED_SANDSTONE,             Blocks.SMOOTH_SANDSTONE),
                    Map.entry(Blocks.SMOOTH_SANDSTONE,               Blocks.SANDSTONE),
                    Map.entry(Blocks.RED_SANDSTONE,                  Blocks.CHISELED_RED_SANDSTONE),
                    Map.entry(Blocks.CHISELED_RED_SANDSTONE,         Blocks.SMOOTH_RED_SANDSTONE),
                    Map.entry(Blocks.SMOOTH_RED_SANDSTONE,           Blocks.RED_SANDSTONE),

                    // === Polished stone variants ===
                    Map.entry(Blocks.GRANITE,                        Blocks.POLISHED_GRANITE),
                    Map.entry(Blocks.POLISHED_GRANITE,               Blocks.GRANITE),
                    Map.entry(Blocks.DIORITE,                        Blocks.POLISHED_DIORITE),
                    Map.entry(Blocks.POLISHED_DIORITE,               Blocks.DIORITE),
                    Map.entry(Blocks.ANDESITE,                       Blocks.POLISHED_ANDESITE),
                    Map.entry(Blocks.POLISHED_ANDESITE,              Blocks.ANDESITE),

                    // === Quartz ===
                    Map.entry(Blocks.QUARTZ_BLOCK,                   Blocks.CHISELED_QUARTZ_BLOCK),
                    Map.entry(Blocks.CHISELED_QUARTZ_BLOCK,          Blocks.QUARTZ_PILLAR),
                    Map.entry(Blocks.QUARTZ_PILLAR,                  Blocks.SMOOTH_QUARTZ),
                    Map.entry(Blocks.SMOOTH_QUARTZ,                  Blocks.QUARTZ_BLOCK),

                    // === Copper ===
                    Map.entry(Blocks.COPPER_BLOCK,                   Blocks.CUT_COPPER),
                    Map.entry(Blocks.EXPOSED_COPPER,                 Blocks.EXPOSED_CUT_COPPER),
                    Map.entry(Blocks.WEATHERED_COPPER,               Blocks.WEATHERED_CUT_COPPER),
                    Map.entry(Blocks.OXIDIZED_COPPER,                Blocks.OXIDIZED_CUT_COPPER),
                    Map.entry(Blocks.CUT_COPPER,                     Blocks.COPPER_BLOCK),
                    Map.entry(Blocks.EXPOSED_CUT_COPPER,             Blocks.EXPOSED_COPPER),
                    Map.entry(Blocks.WEATHERED_CUT_COPPER,           Blocks.WEATHERED_COPPER),
                    Map.entry(Blocks.OXIDIZED_CUT_COPPER,            Blocks.OXIDIZED_COPPER),

                    // === Purpur ===
                    Map.entry(Blocks.PURPUR_BLOCK,                   Blocks.PURPUR_PILLAR),
                    Map.entry(Blocks.PURPUR_PILLAR,                  Blocks.PURPUR_BLOCK)
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }


    return InteractionResult.SUCCESS;
    }
}
