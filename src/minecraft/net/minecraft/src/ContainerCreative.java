package net.minecraft.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//Spout start
import org.spoutcraft.client.MCItemStackComparator;
import org.spoutcraft.spoutcraftapi.material.MaterialData;

import com.google.common.collect.Lists;
//Spout end

class ContainerCreative extends Container {

	public List<ItemStack> itemList = new ArrayList<ItemStack>(500); //Spout

	public ContainerCreative(EntityPlayer par1EntityPlayer) {
		/* Spout start
		Block[] var2 = new Block[]{Block.cobblestone, Block.stone, Block.oreDiamond, Block.oreGold, Block.oreIron, Block.oreCoal, Block.oreLapis, Block.oreRedstone, Block.stoneBrick, Block.stoneBrick, Block.stoneBrick, Block.stoneBrick, Block.blockClay, Block.blockDiamond, Block.blockGold, Block.blockSteel, Block.bedrock, Block.blockLapis, Block.brick, Block.cobblestoneMossy, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.stairSingle, Block.obsidian, Block.netherrack, Block.slowSand, Block.glowStone, Block.wood, Block.wood, Block.wood, Block.wood, Block.leaves, Block.leaves, Block.leaves, Block.leaves, Block.dirt, Block.grass, Block.sand, Block.sandStone, Block.sandStone, Block.sandStone, Block.gravel, Block.web, Block.planks, Block.planks, Block.planks, Block.planks, Block.sapling, Block.sapling, Block.sapling, Block.sapling, Block.deadBush, Block.sponge, Block.ice, Block.blockSnow, Block.plantYellow, Block.plantRed, Block.mushroomBrown, Block.mushroomRed, Block.cactus, Block.melon, Block.pumpkin, Block.pumpkinLantern, Block.vine, Block.fenceIron, Block.thinGlass, Block.netherBrick, Block.netherFence, Block.stairsNetherBrick, Block.whiteStone, Block.mycelium, Block.waterlily, Block.tallGrass, Block.tallGrass, Block.chest, Block.workbench, Block.glass, Block.tnt, Block.bookShelf, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.cloth, Block.dispenser, Block.stoneOvenIdle, Block.music, Block.jukebox, Block.pistonStickyBase, Block.pistonBase, Block.fence, Block.fenceGate, Block.ladder, Block.rail, Block.railPowered, Block.railDetector, Block.torchWood, Block.stairCompactPlanks, Block.stairCompactCobblestone, Block.stairsBrick, Block.stairsStoneBrickSmooth, Block.lever, Block.pressurePlateStone, Block.pressurePlatePlanks, Block.torchRedstoneActive, Block.button, Block.trapdoor, Block.enchantmentTable, Block.redstoneLampIdle};
		int var3 = 0;
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		int var7 = 0;
		int var8 = 0;
		int var9 = 0;
		int var10 = 0;
		int var11 = 1;

		int var12;
		int var13;
		for (var12 = 0; var12 < var2.length; ++var12) {
			var13 = 0;
			if (var2[var12] == Block.cloth) {
				var13 = var3++;
			} else if (var2[var12] == Block.stairSingle) {
				var13 = var4++;
			} else if (var2[var12] == Block.wood) {
				var13 = var5++;
			} else if (var2[var12] == Block.planks) {
				var13 = var6++;
			} else if (var2[var12] == Block.sapling) {
				var13 = var7++;
			} else if (var2[var12] == Block.stoneBrick) {
				var13 = var8++;
			} else if (var2[var12] == Block.sandStone) {
				var13 = var9++;
			} else if (var2[var12] == Block.tallGrass) {
				var13 = var11++;
			} else if (var2[var12] == Block.leaves) {
				var13 = var10++;
			}

			this.itemList.add(new ItemStack(var2[var12], 1, var13));
		}

		for (var12 = 256; var12 < Item.itemsList.length; ++var12) {
			if (Item.itemsList[var12] != null && Item.itemsList[var12].shiftedIndex != Item.potion.shiftedIndex && Item.itemsList[var12].shiftedIndex != Item.monsterPlacer.shiftedIndex) {
				this.itemList.add(new ItemStack(Item.itemsList[var12]));
			}
		}

		for (var12 = 1; var12 < 16; ++var12) {
			this.itemList.add(new ItemStack(Item.dyePowder.shiftedIndex, 1, var12));
		}

		Iterator var15 = EntityList.entityEggs.keySet().iterator();

		while (var15.hasNext()) {
			Integer var17 = (Integer)var15.next();
			this.itemList.add(new ItemStack(Item.monsterPlacer.shiftedIndex, 1, var17.intValue()));
		}
		*/
		
		for (org.spoutcraft.spoutcraftapi.material.Material mat : MaterialData.getMaterials()) {
			//don't render custoom blocks, because their items are already in this list
			if (!(mat instanceof org.spoutcraft.spoutcraftapi.material.CustomBlock)) { 
				ItemStack next = new ItemStack(mat.getRawId(), 1, mat.getRawData());
				if (next.getItem() != null) {
					this.itemList.add(next);
				}
			}
		}
		Collections.sort(itemList, new MCItemStackComparator());
		
		int var11;
		//Spout end

		InventoryPlayer var13 = par1EntityPlayer.inventory;

		for (var11 = 0; var11 < 9; ++var11) {
			for (int var12 = 0; var12 < 8; ++var12) {
				this.addSlot(new Slot(GuiContainerCreative.getInventory(), var12 + var11 * 8, 8 + var12 * 18, 18 + var11 * 18));
			}
		}

		for (var11 = 0; var11 < 9; ++var11) {
			this.addSlot(new Slot(var13, var11, 8 + var11 * 18, 184));
		}

		this.scrollTo(0.0F);
	}
	
	//Spout start
	public IInventory getInventory() {
		return null;
	}
	//Spout end

	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return true;
	}

	public void scrollTo(float par1) {
		int var2 = this.itemList.size() / 8 - 8 + 1;
		int var3 = (int)((double)(par1 * (float)var2) + 0.5D);
		if (var3 < 0) {
			var3 = 0;
		}

		for (int var4 = 0; var4 < 9; ++var4) {
			for (int var5 = 0; var5 < 8; ++var5) {
				int var6 = var5 + (var4 + var3) * 8;
				if (var6 >= 0 && var6 < this.itemList.size()) {
					GuiContainerCreative.getInventory().setInventorySlotContents(var5 + var4 * 8, (ItemStack)this.itemList.get(var6));
				} else {
					GuiContainerCreative.getInventory().setInventorySlotContents(var5 + var4 * 8, (ItemStack)null);
				}
			}
		}
	}

	protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {}
}
