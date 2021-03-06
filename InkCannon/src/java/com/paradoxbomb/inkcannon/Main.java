/*
 * Ink Cannon Mod for Minecraft 1.8.9
 * Programmed by David Small starting on 18 January 2016
 */

package com.paradoxbomb.inkcannon;


import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

//tell Forge that this is a mod and to treat it as such
//modid refers to an internal id (must be all lower-case)
//name is the string that will be viewable to the player
//version is the version number, used in some screens
//dependencies is any mod that this mod requires to run
//useMetadata tells Forge to use the metadata described here
@Mod(modid = StringLib.MODID, name = StringLib.MODNAME, version = StringLib.VERSION, dependencies = StringLib.MIN_FORGE , useMetadata = true)
public class Main 
{
	//set up proxies
	@SidedProxy(clientSide = "com.paradoxbomb.inkcannon.ClientProxy", serverSide="com.paradoxbomb.inkcannon.ServerProxy")
	public static CommonProxy proxy;
	
	//create mod instance
	@Mod.Instance
	public static Main instance = new Main();
	
	//create logger
	public static Logger logger;
	
	//Forge Mod Loader event handlers
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		LogHelper.info("Entering PreInit phase for mod Ink Cannon");
		proxy.preinit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		LogHelper.info("Entering Init phase for mod Ink Cannon");
		proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Entering PostInit phase for mod Ink Cannon");
		proxy.postInit(event);
	}
	
}
