/*
 * File to hold commands that need to be run through both the server and client proxies
 */

package com.paradoxbomb.inkcannon;

import com.paradoxbomb.inkcannon.common.blocks.ModBlocks;
import com.paradoxbomb.inkcannon.common.items.ModItems;
import com.paradoxbomb.inkcannon.common.misc.Crafting;
import com.paradoxbomb.inkcannon.common.tileEntities.ModTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	public void preinit(FMLPreInitializationEvent event)
	{
		ModItems.createItems();				//initialize mod items
		ModBlocks.createBlocks();			//initialize mod blocks
		ModTileEntities.init(); 			//initialize tile entities
	}
	
	public void init(FMLInitializationEvent event)
	{
		Crafting.initCrafting();
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
