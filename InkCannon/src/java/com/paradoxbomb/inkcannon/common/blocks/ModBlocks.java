//file to hold blocks for registration

package com.paradoxbomb.inkcannon.common.blocks;

import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock.BlockCanvas;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks 
{
		public static Block testBlock;
		public static Block blockCanvas;
	
		public static void createBlocks()
		{
			GameRegistry.registerBlock(testBlock = new TestBlock(StringLib.TEST_BLOCK), StringLib.TEST_BLOCK);
			GameRegistry.registerBlock(blockCanvas = new BlockCanvas(StringLib.CANVAS_BLOCK), StringLib.CANVAS_BLOCK);
		}
}
