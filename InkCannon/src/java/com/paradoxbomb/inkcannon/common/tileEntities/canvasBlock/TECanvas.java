package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import com.paradoxbomb.inkcannon.LogHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TECanvas extends TileEntity   
{
	private IBlockState disguisedBlock;
	
	public TECanvas()
	{
		LogHelper.info("This shouldn't be possible!");
	}
	
	public TECanvas(World worldIn)
	{
		LogHelper.info("received object World");
	}
	
	public void setDisguiseState(IBlockState newState)
	{
		this.disguisedBlock = newState;
	}
	
	public IBlockState getDisguisedState()
	{
		return this.disguisedBlock;
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		LogHelper.info("Saving data to NBT");
		super.writeToNBT(compound);
		
	}
	
	 @Override
	 public void readFromNBT(NBTTagCompound compound)
	 {
		 
	 }

}
