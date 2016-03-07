package com.paradoxbomb.inkcannon.client.render;

import com.paradoxbomb.inkcannon.StringLib;
import com.paradoxbomb.inkcannon.common.entities.EntityInkBlob;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderInkBlob extends Render<EntityInkBlob> implements IRenderFactory<EntityInkBlob>
{
    
    public RenderInkBlob (RenderManager renderManagerIn)
    {
    	super(renderManagerIn);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(EntityInkBlob entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        this.bindTexture(TextureMap.locationBlocksTexture);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture1(EntityInkBlob entity)
    {
        return TextureMap.locationBlocksTexture;
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityInkBlob entity) 
	{
		ResourceLocation location = new ResourceLocation(StringLib.MODID + ":textures/entities/EntityInkBlob.png");
		return location;
	}

	@Override
	public Render createRenderFor(RenderManager manager) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
