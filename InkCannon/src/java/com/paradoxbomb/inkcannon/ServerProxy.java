/*
 * File to hold commands that need to be run only on the server side (tile entity ticking, world alteration, etc)
 */

package com.paradoxbomb.inkcannon;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy 
{

	@Override
	public void preinit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		super.preinit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		super.postInit(event);
	}

}
