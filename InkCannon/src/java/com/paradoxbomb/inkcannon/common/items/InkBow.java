//main item for the mod

package com.paradoxbomb.inkcannon.common.items;

import com.paradoxbomb.inkcannon.common.entities.EntityInkArrow;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class InkBow extends Item
{

	public InkBow(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if(!worldIn.isRemote)
		{
			worldIn.spawnEntityInWorld(new EntityInkArrow(worldIn, playerIn, 15));
		}
		
		return itemStackIn;
	}

}
