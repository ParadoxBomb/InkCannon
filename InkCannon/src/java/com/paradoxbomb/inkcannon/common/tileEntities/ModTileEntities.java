package com.paradoxbomb.inkcannon.common.tileEntities;

import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock.TECanvas;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities 
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TECanvas.class, StringLib.CANVAS_ENTITY);
	}
}
