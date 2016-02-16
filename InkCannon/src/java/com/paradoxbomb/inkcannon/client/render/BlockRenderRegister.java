package com.paradoxbomb.inkcannon.client.render;

import com.paradoxbomb.inkcannon.LogHelper;
import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public final class BlockRenderRegister 
{
	public static void registerBlockRenderer()
	{
		LogHelper.info("Registering blocks...");
		reg(ModBlocks.testBlock, 0);
		reg(ModBlocks.metaBlock, 0, "block_properties_white");
		reg(ModBlocks.metaBlock, 1, "block_properties_black");
		LogHelper.info("Block registration complete.");
	}
	
	public static void reg(Block block, int meta )
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(StringLib.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void reg(Block block, int meta, String file)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(StringLib.MODID + ":" + file, "inventory"));
	}
	
	public static void preInitBlockstates()
	{
		ModelBakery.addVariantName(Item.getItemFromBlock(ModBlocks.metaBlock), StringLib.MODID+":block_properties_black", StringLib.MODID+":block_properties_white");
	}
}
