//file to assist CanvasBlockModelFactory in creating the model

package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertyPaintedBlock implements IUnlistedProperty<IBlockState>
{

	@Override
	public String getName() 
	{
		return "UnlistedPropertyPaintedBlock";
	}

	@Override
	public boolean isValid(IBlockState value) 
	{
		return true;
	}

	@Override
	public Class<IBlockState> getType() 
	{
		return IBlockState.class;
	}

	@Override
	public String valueToString(IBlockState value) 
	{
		return value.toString();
	}

}
