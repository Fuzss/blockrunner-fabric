package fuzs.blockrunner.mixin;

import fuzs.blockrunner.api.event.entity.player.ItemTooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getTooltipLines", at = @At("TAIL"))
    public void getTooltipLines$tail(@Nullable Player player, TooltipFlag tooltipFlag, CallbackInfoReturnable<List<Component>> callbackInfo) {
        List<Component> lines = callbackInfo.getReturnValue();
        ItemTooltipCallback.EVENT.invoker().onItemTooltip((ItemStack) (Object) this, player, lines, tooltipFlag);
    }
}
