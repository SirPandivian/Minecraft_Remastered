package sirpandivian.minecraft.remastered.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import sirpandivian.minecraft.remastered.blocks.BlocksMain;

import static sirpandivian.minecraft.remastered.RemasteredMain.MODID;

public class ItemsMain {
    /*
    Registers
    */

    // Item Registry; Registers items into minecraft
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Creative Mode Tab Registry; Registers creative mode tabs (and adds items into them) into minecraft
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    /*
    Items
    */

    // Creates a new food item with the id "minecraft_remastered:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    /*
    Block Items
    */

    // Example Block Item; Example block item
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", BlocksMain.EXAMPLE_BLOCK);

    /*
    Creative Mode Tabs
    */

    // Example Tab Creative Mode Tab; Adds the creative mode tab "Example_Tab" and its items
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.minecraft_remastered")).withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> EXAMPLE_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(EXAMPLE_ITEM.get());
        output.accept(EXAMPLE_BLOCK_ITEM.get());
    }).build());

    // Add items to default minecraft tabs
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) event.accept(EXAMPLE_BLOCK_ITEM);
    }
}
