//basic form for Block class

package com.paradoxbomb.inkcannon.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TestBlock extends Block 
{
	private static float HARDNESS = 2.0f;
	private static float RESISTANCE = 10.0f;
	private static Material DEFAULT_MATERIAL = Material.rock;
	private static CreativeTabs TAB = CreativeTabs.tabBlock;
	
	//full constructor that will be "simplified" below
	public TestBlock (String unlocalizedName, Material material,float hardness, float resistance)
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);	//sets the block's unlocalized name
		this.setCreativeTab(TAB);					//sets which creative tab the block will appear in, defaults to tabBlock
		this.setHardness(hardness);					//sets the time it takes to break the block, default depends on material
		this.setResistance(resistance);				//sets the block's resistance to explosions
		this.setStepSound(soundTypeStone);			//sets what sound will play when the player walks on the block
		this.setLightOpacity(16);					//how much light will be subtrached when passing through the block; ranges from 16 (full opaque) to 0 (transparent)
		//this.setLightLevel(0.2f);					//sets how much light the block emits - 0.0f (nothing) to 1.0f (full sunlight). Defaults to 0.0f
		this.setHarvestLevel("pickaxe", 1);			//sets tool and harvest level for the block - vanilla tools are "pickaxe", "axe", and "shovel". Vanilla harvest levels are ints from 0-3 inclusive.
		//this.setBlockUnbreakable();				//causes the block to be unbreakable similar to bedrock and barriers.
		//this.setTickRandomly(true);				//sets whether or not the block should receive random update ticks similar to leaves. Defaults to false.
	}
	
	public TestBlock (String unlocalizedName, float hardness, float resistance)
	{
		this(unlocalizedName, DEFAULT_MATERIAL, hardness, resistance);
	}
	
	public TestBlock(String unlocalizedName)
	{
		this(unlocalizedName,HARDNESS,RESISTANCE);
	}
	
}
