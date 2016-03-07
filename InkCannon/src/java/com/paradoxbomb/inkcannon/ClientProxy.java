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
	public void preinit(FMLPreInitializationEvent event) 
	{
		super.preinit(event);
		//register block states so that the render register can do its thing in init phase
		//BlockRenderRegister.preInitBlockstates();
	}

	@Override
	public void init(FMLInitializationEvent event) 
	{
		super.init(event);
		//register items and block to be rendered
		ItemRenderRegister.registerItemRenderer();
		BlockRenderRegister.registerBlockRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) 
	{
		super.postInit(event);
	}

}
