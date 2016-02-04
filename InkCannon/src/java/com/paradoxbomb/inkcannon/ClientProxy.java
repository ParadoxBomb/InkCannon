/*
 * File to hold commands that need to be run only on the client side (rendering, UI, etc)
 */

package com.paradoxbomb.inkcannon;

import com.paradoxbomb.inkcannon.client.render.BlockRenderRegister;
import com.paradoxbomb.inkcannon.client.render.ItemRenderRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{

	@Override
	public void preinit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		super.preinit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ItemRenderRegister.registerItemRenderer();
		BlockRenderRegister.registerBlockRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		super.postInit(event);
	}

}
