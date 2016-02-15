//file to hold crafting recipes

package com.paradoxbomb.inkcannon.common.misc;

import com.paradoxbomb.inkcannon.LogHelper;
import com.paradoxbomb.inkcannon.common.blocks.ModBlocks;
import com.paradoxbomb.inkcannon.common.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Crafting 
{
	//initalizes crafting recipes, called during init phase
	public static void initCrafting()
	{
		LogHelper.info("Regostering mod recipes...");
		//shaped
		GameRegistry.addRecipe(new ItemStack(ModBlocks.testBlock), new Object[] {"##", "##", '#', ModItems.testItem});
		GameRegistry.addRecipe(new ItemStack(ModItems.inkBow), new Object [] {"#i|", "iU|", "#i|", 'i', Items.stick, '|', Items.string, 'U', Items.bucket, '#', Items.iron_ingot});
		
		//shapeless
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.testItem), new Object [] {Items.redstone, new ItemStack(Items.dye, 1, 4)});
	}
}
