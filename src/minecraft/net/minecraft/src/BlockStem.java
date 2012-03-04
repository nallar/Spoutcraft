package net.minecraft.src;

import com.pclewis.mcpatcher.mod.Colorizer; //Spout HD
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.EntityItem;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class BlockStem extends BlockFlower {

	private Block fruitType;

	protected BlockStem(int par1, Block par2Block) {
		super(par1, 111);
		this.fruitType = par2Block;
		this.setTickRandomly(true);
		float var3 = 0.125F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int par1) {
		return par1 == Block.tilledField.blockID;
	}

	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
			float var6 = this.getGrowthModifier(par1World, par2, par3, par4);
			if (par5Random.nextInt((int)(25.0F / var6) + 1) == 0) {
				int var7 = par1World.getBlockMetadata(par2, par3, par4);
				if (var7 < 7) {
					++var7;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, var7);
				} else {
					if (par1World.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID) {
						return;
					}

					if (par1World.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID) {
						return;
					}

					if (par1World.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID) {
						return;
					}

					if (par1World.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID) {
						return;
					}

					int var8 = par5Random.nextInt(4);
					int var9 = par2;
					int var10 = par4;
					if (var8 == 0) {
						var9 = par2 - 1;
					}

					if (var8 == 1) {
						++var9;
					}

					if (var8 == 2) {
						var10 = par4 - 1;
					}

					if (var8 == 3) {
						++var10;
					}

					int var11 = par1World.getBlockId(var9, par3 - 1, var10);
					if (par1World.getBlockId(var9, par3, var10) == 0 && (var11 == Block.tilledField.blockID || var11 == Block.dirt.blockID || var11 == Block.grass.blockID)) {
						par1World.setBlockWithNotify(var9, par3, var10, this.fruitType.blockID);
					}
				}
			}
		}

	}

	public void fertilizeStem(World par1World, int par2, int par3, int par4) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4, 7);
	}

	private float getGrowthModifier(World par1World, int par2, int par3, int par4) {
		float var5 = 1.0F;
		int var6 = par1World.getBlockId(par2, par3, par4 - 1);
		int var7 = par1World.getBlockId(par2, par3, par4 + 1);
		int var8 = par1World.getBlockId(par2 - 1, par3, par4);
		int var9 = par1World.getBlockId(par2 + 1, par3, par4);
		int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
		int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
		int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
		int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
		boolean var14 = var8 == this.blockID || var9 == this.blockID;
		boolean var15 = var6 == this.blockID || var7 == this.blockID;
		boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

		for (int var17 = par2 - 1; var17 <= par2 + 1; ++var17) {
			for (int var18 = par4 - 1; var18 <= par4 + 1; ++var18) {
				int var19 = par1World.getBlockId(var17, par3 - 1, var18);
				float var20 = 0.0F;
				if (var19 == Block.tilledField.blockID) {
					var20 = 1.0F;
					if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0) {
						var20 = 3.0F;
					}
				}

				if (var17 != par2 || var18 != par4) {
					var20 /= 4.0F;
				}

				var5 += var20;
			}
		}

		if (var16 || var14 && var15) {
			var5 /= 2.0F;
		}

		return var5;
	}

	public int getRenderColor(int par1) {
		int var2 = par1 * 32;
		int var3 = 255 - par1 * 8;
		int var4 = par1 * 4;
		return Colorizer.colorizeStem(var2 << 16 | var3 << 8 | var4, par1);  //Spout HD
	}

	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return this.getRenderColor(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return this.blockIndexInTexture;
	}

	public void setBlockBoundsForItemRender() {
		float var1 = 0.125F;
		this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.maxY = (double)((float)(par1IBlockAccess.getBlockMetadata(par2, par3, par4) * 2 + 2) / 16.0F);
		float var5 = 0.125F;
		this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var5, 0.5F + var5, (float)this.maxY, 0.5F + var5);
	}

	public int getRenderType() {
		return 19;
	}

	public int func_35296_f(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		return var5 < 7?-1:(par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID?0:(par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID?1:(par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID?2:(par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID?3:-1))));
	}

	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		if (!par1World.isRemote) {
			Item var8 = null;
			if (this.fruitType == Block.pumpkin) {
				var8 = Item.pumpkinSeeds;
			}

			if (this.fruitType == Block.melon) {
				var8 = Item.melonSeeds;
			}

			for (int var9 = 0; var9 < 3; ++var9) {
				if (par1World.rand.nextInt(15) <= par5) {
					float var10 = 0.7F;
					float var11 = par1World.rand.nextFloat() * var10 + (1.0F - var10) * 0.5F;
					float var12 = par1World.rand.nextFloat() * var10 + (1.0F - var10) * 0.5F;
					float var13 = par1World.rand.nextFloat() * var10 + (1.0F - var10) * 0.5F;
					EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var11), (double)((float)par3 + var12), (double)((float)par4 + var13), new ItemStack(var8));
					var14.delayBeforeCanPickup = 10;
					par1World.spawnEntityInWorld(var14);
				}
			}

		}
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		if (par1 == 7) {
			;
		}

		return -1;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}
}
