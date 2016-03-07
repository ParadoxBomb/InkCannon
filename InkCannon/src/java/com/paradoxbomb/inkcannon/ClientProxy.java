/*
 * File to hold commands that need to be run only on the client side (rendering, UI, etc)
 */

package com.paradoxbomb.inkcannon;

import com.paradoxbomb.inkcannon.client.render.BlockRenderRegister;
import com.paradoxbomb.inkcannon.client.render.ItemRenderRegister;
import com.paradoxbomb.inkcannon.client.render.ModelBakeEventHandler;
import com.paradoxbomb.inkcannon.common.blocks.ModBlocks;
import com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock.BlockCanvas;
import com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock.CanvasBlockModelFactory;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy 
{

	@Override
	public void preinit(FMLPreInitializationEvent event) 
	{
		super.preinit(event);
		
		//tells Forge how to map BlockCanvas's IBlockState onto ModelResourceLocation
		//since this block is special, an anonymous class is used instead of the normal methos
		StateMapperBase ignoreState = new StateMapperBase()
				{
					@Override
					protected ModelResourceLocation getModelResourceLocation(IBlockState blockState)
					{
						return CanvasBlockModelFactory.modelResourceLocation;
					}
				};
		ModelLoader.setCustomStateMapper((BlockCanvas)ModBlocks.blockCanvas, ignoreState);
		
		//register the custom event handler
		MinecraftForge.EVENT_BUS.register(ModelBakeEventHandler.instance);
		
		//make canvas block render properly while an item
		Item itemBlockCanvas = GameRegistry.findItem(StringLib.MODID, StringLib.CANVAS_BLOCK);
		ModelResourceLocation itemModelResourceLoaction = new ModelResourceLocation(StringLib.MODID+":"+StringLib.CANVAS_BLOCK, "inventory");
		final int DEFAULT_ITEM_SUBTYPE = 0;
		ModelLoader.setCustomModelResourceLocation(itemBlockCanvas, DEFAULT_ITEM_SUBTYPE, itemModelResourceLoaction);
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
