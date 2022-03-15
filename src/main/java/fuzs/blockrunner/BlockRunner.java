package fuzs.blockrunner;

import fuzs.blockrunner.data.BlockSpeedManager;
import fuzs.blockrunner.network.message.S2CBlockSpeedMessage;
import fuzs.puzzleslib.network.MessageDirection;
import fuzs.puzzleslib.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockRunner implements ModInitializer {
    public static final String MOD_ID = "blockrunner";
    public static final String MOD_NAME = "Block Runner";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final NetworkHandler NETWORK = NetworkHandler.of(MOD_ID);

    public static void onConstructMod() {
        registerHandlers();
        registerMessages();
    }

    private static void registerHandlers() {
        ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {
            BlockSpeedManager.INSTANCE.load();
        });
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((MinecraftServer server, CloseableResourceManager resourceManager, boolean success) -> {
            BlockSpeedManager.INSTANCE.load();
        });
        ServerPlayConnectionEvents.JOIN.register(BlockSpeedManager.INSTANCE::onPlayerLoggedIn);
    }

    private static void registerMessages() {
        NETWORK.register(S2CBlockSpeedMessage.class, S2CBlockSpeedMessage::new, MessageDirection.TO_CLIENT);
    }

    @Override
    public void onInitialize() {
        onConstructMod();
    }
}
