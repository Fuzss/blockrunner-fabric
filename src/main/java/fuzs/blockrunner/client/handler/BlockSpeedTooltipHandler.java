package fuzs.blockrunner.client.handler;

import fuzs.blockrunner.data.BlockSpeedManager;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockSpeedTooltipHandler {
    public void onItemTooltip(ItemStack stack, @Nullable Player player, List<Component> lines, TooltipFlag flags) {
        if (stack.getItem() instanceof BlockItem item) {
            Block block = item.getBlock();
            if (BlockSpeedManager.INSTANCE.hasCustomSpeed(block)) {
                lines.add(new TranslatableComponent("block.blockrunner.speed_multiplier", BlockSpeedManager.INSTANCE.getSpeedFactor(block)).withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
