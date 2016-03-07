//class to assist in creating the model for canvas blocks
//code based on http://bit.ly/1R1SbXO
//
package com.paradoxbomb.inkcannon.common.tileEntities.canvasBlock;

import java.util.List;

import com.paradoxbomb.inkcannon.LogHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.common.property.IExtendedBlockState;

@SuppressWarnings("deprecation")
public class CanvasBlockModelFactory implements ISmartBlockModel 
{
	private IBakedModel modelWhenNotPainted;
	
	public CanvasBlockModelFactory (IBakedModel unPaintedBlock)
	{
		modelWhenNotPainted = unPaintedBlock;
	}
	
	//creates model resource location for canvas block
	public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation("inkcannon:blockCanvas");

	// creates an IBakedModel based on the IBlockState of the block state that is passed
	@Override
	public IBakedModel handleBlockState(IBlockState state) 
	{
		IBakedModel returnModel = modelWhenNotPainted;	//default
		IBlockState unPaintedBlock = Blocks.air.getDefaultState();
		
		if (state instanceof IExtendedBlockState)
		{
			IExtendedBlockState extendedState = (IExtendedBlockState)state;
			IBlockState paintedBlockIBlockState = extendedState.getValue(BlockCanvas.PAINTED_BLOCK);
			
			if (paintedBlockIBlockState != unPaintedBlock)
			{
				Minecraft mc = Minecraft.getMinecraft();
				BlockRendererDispatcher blockRendererDispatcher = mc.getBlockRendererDispatcher();
				BlockModelShapes blockModelShapes = blockRendererDispatcher.getBlockModelShapes();
				IBakedModel copiedBlockModel = blockModelShapes.getModelForState(paintedBlockIBlockState);
				
				if(copiedBlockModel instanceof ISmartBlockModel)
				{
					copiedBlockModel = ((ISmartBlockModel)copiedBlockModel).handleBlockState(paintedBlockIBlockState);
				}
				returnModel = copiedBlockModel;
			}
		}
		
		return returnModel;
	}
	
	//used in case of a player being inside a block; game wil crash unless something meaningful is used here
	@Override
	public TextureAtlasSprite getParticleTexture() 
	{
		return modelWhenNotPainted.getParticleTexture();
	}
	
	
	//unused methods; would only be used in case of other mod blocks
	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing p_177551_1_) 
	{
		LogHelper.error("Unsupported render method getFaceQuads accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		throw new UnsupportedOperationException();
	}

	@Override
	public List<BakedQuad> getGeneralQuads() 
	{
		LogHelper.error("Unsupported render method getgeneralQuads accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAmbientOcclusion() 
	{
		LogHelper.error("Unsuppoerted method isAmbientOcclusion accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isGui3d() 
	{
		LogHelper.error("Unsuppoerted method isGui3d accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() 
	{
		LogHelper.error("Unsuppoerted method isBuiltInRenderer accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		throw new UnsupportedOperationException();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		LogHelper.error("Unsuppoerted method getItemCameraTransform accessed from mod Ink Cannon!\nPlease contact the mod developer!");
		throw new UnsupportedOperationException();
	}

	

}
