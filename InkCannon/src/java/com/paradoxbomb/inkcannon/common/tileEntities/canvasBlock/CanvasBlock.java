/*
 * Block that will copy the block it replaces and allow for usage of OpenGL shaders to tint it while in world
 * Based on code by TheGreyGhost's MinecraftByExample project, found at https://github.com/TheGreyGhost/MinecraftByExample
 

package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class CanvasBlock extends Block
{
	//constructor
	public CanvasBlock()
	{
		//emulate properties of wool ("cloth")
		super (Material.cloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	//block will render in the solid layer
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
	}
	
	/*
	 * tells the renderer that this block is opaque and occupies one full block space
	 * not strictly necessary due to super method returning true but included just in case 
	 * this mod is loaded with others that alter the behavior
	 
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	/*
	 * tells renderer and blocks such as fences that this is a full block
	 * also not strictly necessary but included just in case
	 
	@Override
	public boolean isFullCube()
	{
		return true;
	}
	
	/*
	 * Render using IBakedModel
	 * Not necessary but included just in case
	 
	@Override
	public int getRenderType()
	{
		return 3;	//3 is IBakedModel; method can return -1, 1, 2, or 3
	}
	
	/*
	 * Method to define what states the block has.
	 * Vanilla states are only composed of listed properties and creates a pair of states for each listed property
	 * Forge adds the ability to have properties that are only used for data and do not create additional block states
	 
	@Override
	protected BlockState createBlockState()
	{
		IProperty [] listedProperties = new IProperty[0];	//no listed properties
		IUnlistedProperty [] unlistedProperties = new IUnlistedProperty [] {COPIEDBLOCK};
		return new ExtendedBlockState(this, listedProperties, unlistedProperties);
	}
	
	/*
	 * Method to update unlisted POWER_LEVEL property in IExtendedBlockState to hold
	 * the block that the canvas should camouflage itself as.
	 
	@Override
	public IBlockState getExtendedState(IBlockState stateIn, IBlockAccess worldIn, BlockPos posIn)
	{
		if (stateIn instanceof IExtendedBlockState)	//avoids crash in case of mismatch
		{
			IExtendedBlockState retVal = (IExtendedBlockState)stateIn;
			IBlockState bestAdjacentBlock = selectBestAdjacentBlock(worldIn, posIn);
			retVal = retVal.withProperty(COPIEDBLOCK, bestAdjacentBlock);
			return retVal;
		}
		
		return stateIn;
	}
	
	//debugging method used as breakpoint placement
	@Override
	public IBlockState getActualState (IBlockState state, IBlockAccess worldIn, BlockPos posIn)
	{
		return state;
	}
	
	public static final UnlistedPropertyCopiedBlock COPIEDBLOCK = new UnlistedPropertyCopiedBlock();
	
	/*
	 * Selects the best adjacent block to camouflage as
	 * Algorithm is as follows:
	 * 	1) Ignore any non-solid blocks (rendered on CUTOUT or TRANSLUCENT layer), ignore adjacent canvas blocks
	 * 	2) If there is more than one type of block in the surrounding area, pick the one with the most representation
	 * 	3) In case of a tie, prefer the type that is opposite of the blockpos e.g.: up/down, east/west, north/south
	 * 	4) If there is still a tie, redo the above accounting for adjacent canvas blocks
	 * 	5) If there is still a tie, look in decreasing order of preference for the count: NORTH, SOUTH, EAST, WEST, DOWN, UP
	 * 	6) If somehow there is still a tie, default to Blocks.wool
	 
	private IBlockState selectBestAdjacentBlock (IBlockAccess world, BlockPos blockPos)
	{
		//default BlockState to use as default
		final IBlockState UNCAMO_BLOCK = Blocks.wool.getDefaultState();
		
		//create a TreeMap to hold the BlockStates of the surrounding blocks
		TreeMap<EnumFacing, IBlockState> adjacentSolidBlocks = new TreeMap <EnumFacing, IBlockState> ();
		
		//create a HashMap to hold the count of adjacent blocks
		HashMap <IBlockState, Integer> adjacentBlockCount = new HashMap <IBlockState, Integer>();
		
		//for each possible facing, loop
		for (EnumFacing facing : EnumFacing.values())
		{
			//get the position in the world of the adjacent block
			BlockPos adjacentPosition = blockPos.add(facing.getFrontOffsetX(),
													  facing.getFrontOffsetY(),
													  facing.getFrontOffsetZ());
			
			//get the state of the block at the aforementioned position
			IBlockState adjacentIBlockState = world.getBlockState(adjacentPosition);
			
			//get the type of block from the aforementioned state
			//this must be done in a backwards manner due to how Minecraft works
			Block adjacentBlock = adjacentIBlockState.getBlock();
			
			//if the adjacent block is not an air block, is rendered on the SOLID layer, and is a full cube block
			if((adjacentBlock != Blocks.air) && (adjacentBlock.getBlockLayer() == EnumWorldBlockLayer.SOLID) 
					&& (adjacentBlock.isOpaqueCube()))
			{
				
				adjacentSolidBlocks.put(facing, adjacentIBlockState);
				if (adjacentBlockCount.containsKey(adjacentIBlockState))
				{
					adjacentBlockCount.put(adjacentIBlockState, 1 + adjacentBlockCount.get(adjacentIBlockState));
				}
				else if (adjacentIBlockState != this)
				{
					adjacentBlockCount.put(adjacentIBlockState, 1);
				}
			}
		}
		
		//if no blocks were found, return the default
		if(adjacentBlockCount.isEmpty())
		{
			return UNCAMO_BLOCK;
		}
		
		//if there is exactly one adjacent block, return the state of that block
		if (adjacentSolidBlocks.size() == 1)
		{
			IBlockState singleAdjacentBlock = adjacentSolidBlocks.firstEntry().getValue();
			
			//...unless that adjacent block is also a canvas block
			if (singleAdjacentBlock.getBlock() == this)
			{
				return UNCAMO_BLOCK;
			}
			else
			{
				return singleAdjacentBlock;
			}
		}
		
		int maxCount = 0;
		ArrayList<IBlockState> maxCountIBlockStates = new ArrayList <IBlockState>();
		for(Map.Entry<IBlockState, Integer> entry : adjacentBlockCount.entrySet())
		{
			if (entry.getValue() > maxCount)
			{
				maxCountIBlockStates
			}
		}
	}
}
*/