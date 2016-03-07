//class to allow dynamically altering Moderlmanager's registry

package com.paradoxbomb.inkcannon.client.render;

import com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock.CanvasBlockModelFactory;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeEventHandler 
{
	public static final ModelBakeEventHandler instance = new ModelBakeEventHandler();
	
	private ModelBakeEventHandler() {};
	
	@SubscribeEvent
	public void onModelBakeEvent(ModelBakeEvent e)
	{
		Object object = e.modelRegistry.getObject(CanvasBlockModelFactory.modelResourceLocation);
		if (object instanceof IBakedModel)
		{
			IBakedModel existingModel = (IBakedModel)object;
			CanvasBlockModelFactory customModel = new CanvasBlockModelFactory(existingModel);
			e.modelRegistry.putObject(CanvasBlockModelFactory.modelResourceLocation, customModel);
		}
	}
}
