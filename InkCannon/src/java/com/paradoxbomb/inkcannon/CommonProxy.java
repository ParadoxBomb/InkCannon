/*
 * File to hold commands that need to be run through both the server and client proxies
 */

package com.paradoxbomb.inkcannon;

import com.paradoxbomb.inkcannon.common.ModBlocks;
import com.paradoxbomb.inkcannon.common.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	public void preinit(FMLPreInitializationEvent event)
	{
		ModItems.createItems();				//initialize mod items
		ModBlocks.createBlocks();			//initialize mod blocks
	}
	
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
