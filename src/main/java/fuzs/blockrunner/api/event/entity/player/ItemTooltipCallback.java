package fuzs.blockrunner.api.event.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@FunctionalInterface
public interface ItemTooltipCallback {
    Event<ItemTooltipCallback> EVENT = EventFactory.createArrayBacked(ItemTooltipCallback.class, callbacks -> (ItemStack stack, Player player, List<Component> tooltip, TooltipFlag flags) -> {
        for (ItemTooltipCallback callback : callbacks) {
            callback.onItemTooltip(stack, player, tooltip, flags);
        }
    });

    /**
     * called at the end of creating the tooltip for an item stack
     * @param stack the stack the tooltip is building for
     * @param player  player viewing the tooltip, will be null during startup when search trees are created
     * @param lines the assembled tooltip
     * @param flags determines if advanced information is shown on the tooltip
     */
    void onItemTooltip(ItemStack stack, @Nullable Player player, List<Component> lines, TooltipFlag flags);
}
