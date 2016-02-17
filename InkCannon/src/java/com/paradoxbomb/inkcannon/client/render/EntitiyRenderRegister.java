package com.paradoxbomb.inkcannon.client.render;

import com.paradoxbomb.inkcannon.LogHelper;
import com.paradoxbomb.inkcannon.common.entities.EntityInkBlob;

import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntitiyRenderRegister 
{
	public static void registerEntityRenderer()
	{
		LogHelper.info("registering entities...");
		RenderingRegistry.registerEntityRenderingHandler(EntityInkBlob.class, RenderInkBlob::new);
		LogHelper.info("Entity registration complete.");
	}

}
