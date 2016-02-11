package com.paradoxbomb.inkcannon.common.blocks;

import com.paradoxbomb.inkcannon.StringLib;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks 
{
		public static Block testBlock;
		public static Block metaBlock;
	
		public static void createBlocks()
		{
			GameRegistry.registerBlock(testBlock = new TestBlock(StringLib.TEST_BLOCK), StringLib.TEST_BLOCK);
			GameRegistry.registerBlock(metaBlock = new BlockProperties(StringLib.META_BLOCK), BlockWithMeta.class, StringLib.META_BLOCK);
		}
}
