package fuzs.blockrunner.client;

import fuzs.blockrunner.client.handler.BlockSpeedTooltipHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;

public class BlockRunnerClient implements ClientModInitializer {
    public static void onConstructMod() {
        registerHandlers();
    }

    private static void registerHandlers() {
        BlockSpeedTooltipHandler blockSpeedTooltipHandler = new BlockSpeedTooltipHandler();
        ItemTooltipCallback.EVENT.register(blockSpeedTooltipHandler::onItemTooltip);
    }

    @Override
    public void onInitializeClient() {
        onConstructMod();
    }
}
