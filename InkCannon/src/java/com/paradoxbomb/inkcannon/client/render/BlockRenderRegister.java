//file to allow blocks to be registered for rendering

package com.paradoxbomb.inkcannon.client.render;

import com.paradoxbomb.inkcannon.LogHelper;
import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderRegister 
{
	//register blocks for rendering; is called during init
	public static void registerBlockRenderer()
	{
		LogHelper.info("Registering blocks...");
		reg(ModBlocks.testBlock, 0);
		LogHelper.info("Block registration complete.");
	}
	
	//shorthand for registration method for blocks without metadata
	public static void reg(Block block, int meta )
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(StringLib.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
	//shorthand for registration method for blocks with metadata; needs to be called for each separate state
	public static void reg(Block block, int meta, String file)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(StringLib.MODID + ":" + file, "inventory"));
	}
}
