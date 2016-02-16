package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import com.paradoxbomb.inkcannon.StringLib;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCanvas extends Block implements ITileEntityProvider
{
	protected BlockCanvas (String unlocalizedName)
	{
		super(Material.rock);
		this.setUnlocalizedName(StringLib.CANVAS_BLOCK);
		this.setHarvestLevel(null, 0);
		this.setHardness(5.0f);
		this.setResistance(5.0f);
		this.isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TECanvas();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity canvasEntity = worldIn.getTileEntity(pos);
		if (canvasEntity instanceof TECanvas)
		{
			//initialize TE data
		}
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.SOLID;
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