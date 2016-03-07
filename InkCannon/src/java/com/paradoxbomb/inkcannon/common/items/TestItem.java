/*
 * Test item please ignore
 */

package com.paradoxbomb.inkcannon.common.items;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.paradoxbomb.inkcannon.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;

public class TestItem extends Item 
{
	public TestItem(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);	//sets the unlocalized name for the item
		this.setCreativeTab(CreativeTabs.tabMisc);	//makes it so that the item will appear in the "Misc" tab in Creative mode
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
		LogHelper.info(block.toString());
		return false;
	}
}
