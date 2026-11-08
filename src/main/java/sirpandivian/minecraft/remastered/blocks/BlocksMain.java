package sirpandivian.minecraft.remastered.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static sirpandivian.minecraft.remastered.RemasteredMain.MODID;

public class BlocksMain {
    /*
    Registers
    */

    // Create a Deferred Register to hold Blocks which will all be registered under the "remastered" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    /*
    Blocks
    */

    // Example Block; Block that is an example
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
}
