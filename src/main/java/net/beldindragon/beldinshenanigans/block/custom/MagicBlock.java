package net.beldindragon.beldinshenanigans.block.custom;

import net.beldindragon.beldinshenanigans.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if(entity instanceof ItemEntity itemEntity) {
            if(itemEntity.getItem().getItem() == ModItems.BISMUTH.get()) {
                level.playSound(itemEntity, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));

            }
        }
        if(entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 5, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false));
        }


        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        for (int j=1; j<=3; j++) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + 1.0 + random.nextDouble()*0.5;
            double z = pos.getZ() + random.nextDouble();
            level.addParticle(
                    ParticleTypes.ASH,
                    x, y, z,
                    0, 0.1, 0);
        };
        super.animateTick(state, level, pos, random);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.collection.bismuth"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.empty"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.magic_block"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.empty"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.magic_block2"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.magic_block3"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.magic_block4"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.empty"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.press_shift"));
            tooltipComponents.add(Component.translatable("tooltip.beldinshenanigans.empty"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
