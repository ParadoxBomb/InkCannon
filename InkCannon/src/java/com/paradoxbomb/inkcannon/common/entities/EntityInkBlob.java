package com.paradoxbomb.inkcannon.common.entities;

import java.util.List;

import com.paradoxbomb.inkcannon.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityInkBlob extends Entity implements IProjectile
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private boolean hitGround;
	private int ticksInAir;
	public Entity shootingEntity;
	private Block inTile;
	private int inData;
	
	
	public EntityInkBlob(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityInkBlob (World worldIn, EntityLivingBase shooter, float velocity)
	{
		super(worldIn);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 0.0F); 
        LogHelper.info("Launching ink blob towards (" + this.motionX + ", " + this.motionY + ", " + this.motionZ +") with velocity "+ velocity);
	}

	@Override
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) 
	{
        float f = MathHelper.sqrt_double(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D;
        y = y + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D;
        z = z + this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float)(MathHelper.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * 180.0D / Math.PI);
	}

	@Override
	protected void entityInit() 
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		BlockPos blockPos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iBlockState = this.worldObj.getBlockState(blockPos);
		Block block = iBlockState.getBlock();
		
		//if the blob is not in an air block, it has hit the ground
		if (block.getMaterial() != Material.air)
		{
			block.setBlockBoundsBasedOnState(this.worldObj, blockPos);
            AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.worldObj, blockPos, iBlockState);

            if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ)))
            {
                this.hitGround = true;
            }
		}
		
		//if the projectile has hit the ground, kill it
		if (this.hitGround)
		{
        	LogHelper.info("Collided successfully!");
        	LogHelper.info("Collided with:" + block.toString());
			this.setDead();
		}
		else
		{
			Vec3 vec31 = new Vec3(this.posX, this.posY, this.posZ);
            Vec3 vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3, false, true, false);
            vec31 = new Vec3(this.posX, this.posY, this.posZ);
            vec3 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec3 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }
            
            //check for collision with entities
            Entity entity = null;
            List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            
            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5))
                {
                    float f1 = 0.3F;
                    AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double)f1, (double)f1, (double)f1);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);

                    if (movingobjectposition1 != null)
                    {
                        double d1 = vec31.squareDistanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }
            
            //ensure not colliding with null
            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }
            
            //check for collision with another player
            if (movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;

                if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
                {
                    movingobjectposition = null;
                }
            }
            
            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                	 if (!(movingobjectposition.entityHit instanceof EntityEnderman))
                     {
                         this.setDead();
                     }
                	 this.motionX *= -0.10000000149011612D;
                     this.motionY *= -0.10000000149011612D;
                     this.motionZ *= -0.10000000149011612D;
                     this.ticksInAir = 0;
                }
                else
                {
                	BlockPos newBlockPos = movingobjectposition.getBlockPos();
                    this.xTile = newBlockPos.getX();
                    this.yTile = newBlockPos.getY();
                    this.zTile = newBlockPos.getZ();
                    IBlockState newIBlockState = this.worldObj.getBlockState(newBlockPos);
                    this.inTile = newIBlockState.getBlock();
                    this.inData = this.inTile.getMetaFromState(newIBlockState);
                    this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
                    this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
                    this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
                    float velMultiplier = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    this.posX -= this.motionX / (double)velMultiplier * 0.05000000074505806D;
                    this.posY -= this.motionY / (double)velMultiplier * 0.05000000074505806D;
                    this.posZ -= this.motionZ / (double)velMultiplier * 0.05000000074505806D;
                    this.hitGround = true;

                    if (this.inTile.getMaterial() != Material.air)
                    {
                        this.inTile.onEntityCollidedWithBlock(this.worldObj, newBlockPos, newIBlockState, this);
                    }
                }
            }
            
			//if the projectile is not dead, continue moving it
			this.posX += this.motionX;
	        this.posY += this.motionY;
	        this.posZ += this.motionZ;
	        
	        float velocityIncrease = 0.99F;
	        float velocityDecrease = 0.05F;
	        
	        this.motionX *= (double)velocityIncrease;
	        this.motionY *= (double)velocityIncrease;
	        this.motionZ *= (double)velocityIncrease;
	        this.motionY -= (double)velocityDecrease;
	        this.setPosition(this.posX, this.posY, this.posZ);
	        this.doBlockCollisions();
		}
	}
	
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) 
	{
		// TODO Auto-generated method stub
		
	}
}
