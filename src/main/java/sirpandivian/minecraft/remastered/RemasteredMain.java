package sirpandivian.minecraft.remastered;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import sirpandivian.minecraft.remastered.items.ItemsMain;

import static sirpandivian.minecraft.remastered.blocks.BlocksMain.BLOCKS;
import static sirpandivian.minecraft.remastered.items.ItemsMain.CREATIVE_MODE_TABS;
import static sirpandivian.minecraft.remastered.items.ItemsMain.ITEMS;

@Mod(RemasteredMain.MODID)
public class RemasteredMain {
    // ModID
    public static final String MODID = "remastered";
    // Define logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public RemasteredMain(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the config
        modEventBus.register(Config.class);

        // Register client events
        modEventBus.register(ClientModEvents.class);

        /*
        Registry Registers
        */

        // Block Registry
        BLOCKS.register(modEventBus);

        // Item Registry
        ITEMS.register(modEventBus);

        // Creative Mode Tabs Registry
        CREATIVE_MODE_TABS.register(modEventBus);

        // Server & Event Register
        NeoForge.EVENT_BUS.register(this);

        // Vanilla Tab Register (add items to minecraft tabs)
        modEventBus.addListener(ItemsMain::addCreative);

        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Common setup code (Run on both client and server)
        LOGGER.info("HELLO FROM COMMON SETUP");

        // Check "Example BooleanValue" and log info if true
        if (Config.logDirtBlock) LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        // Log "magicNumber"
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        // Log list of items
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server setup code (Runs on server)
        LOGGER.info("HELLO from server starting");
    }

    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Client setup code (Runs on client)
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
