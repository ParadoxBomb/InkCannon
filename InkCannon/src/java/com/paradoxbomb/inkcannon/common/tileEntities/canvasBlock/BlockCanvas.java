//block that will replace a block in the world with a copy of its texture that can be tinted

package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.items.ModItems;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCanvas extends BlockContainer
{
	
	public static final UnlistedPropertyPaintedBlock PAINTED_BLOCK = new UnlistedPropertyPaintedBlock();
	
	public BlockCanvas (String unlocalizedName)
	{
		super(Material.cloth);
		this.setUnlocalizedName(StringLib.CANVAS_BLOCK);
		this.setCreativeTab(ModItems.tabInkCannon);
		this.setHarvestLevel(Blocks.wool.getHarvestTool(getDefaultState()), Blocks.wool.getHarvestLevel(getDefaultState()));
		this.setHardness(0.8f);
		this.setResistance(5.0f);
	}
		
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TECanvas(worldIn);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BlockState createBlockState()
	{
		IProperty [] listedProperties = new IProperty[0];	//no listed properties
		IUnlistedProperty [] unlistedProperties = new IUnlistedProperty [] {PAINTED_BLOCK};
		return new ExtendedBlockState (this, listedProperties, unlistedProperties);
	}
	
//	@Override
//	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
//	{
//		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
//		TileEntity canvasEntity = worldIn.getTileEntity(pos);
//		if (canvasEntity instanceof TECanvas)
//		{
//			((TECanvas) canvasEntity).setDisguiseState(worldIn.getBlockState(pos));
//		}
//	}
	
	//method to clean up the tile entity when the related block is destroyed
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}
	
	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
	{
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity te = worldIn.getTileEntity(pos);
		return te == null ? false : te.receiveClientEvent(eventID, eventParam);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
	}
	
	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if (state instanceof IExtendedBlockState)
		{
			IExtendedBlockState returnState = (IExtendedBlockState)state;
			returnState = returnState.withProperty(PAINTED_BLOCK, state);
			return returnState;
		}
		return state;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	@Override
	public boolean isFullCube()
	{
		return true;
	}
	
	@Override
	public int getRenderType()
	{
		return 3;
	}


}