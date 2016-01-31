/*
 * Test item please ignore
 */

package com.paradoxbomb.inkcannon.common;


import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public class TestItem extends Item 
{
	public TestItem(String unlocalizedName)
	{
		super();
		this.setUnlocalizedName(unlocalizedName);	//sets the unlocalized name for the item
		this.setCreativeTab(CreativeTabs.tabMisc);	//makes it so that the item will appear in the "Misc" tab in Creative mode
	}
}
