package com.paradoxbomb.inkcannon.common;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks 
{
		public static Block testBlock;
	
		public static void createBlocks()
		{
			GameRegistry.registerBlock(testBlock = new TestBlock(TestBlock.UNLOCALIZED_NAME), TestBlock.UNLOCALIZED_NAME);
		}
}
