package com.hea3ven.buildingbricks.core.items.crafting;

import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

// TODO: Find a better way to fix that normal recipes don't match NBT Tags.
public class RecipeBlockMaterial extends ShapedOreRecipe {

	public RecipeBlockMaterial(ItemStack output, Object... recipe) {
		super(output, recipe);
	}

	@Override
	protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
		for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++) {
			for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++) {
				int subX = x - startX;
				int subY = y - startY;
				Object target = null;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
					if (mirror) {
						target = input[width - subX - 1 + subY * width];
					} else {
						target = input[subX + subY * width];
					}
				}

				ItemStack slot = inv.getStackInRowAndColumn(x, y);

				if (target instanceof ItemStack) {
					ItemStack stack = (ItemStack) target;
					if (!OreDictionary.itemMatches(stack, slot, false)) {
						return false;
					}
					if (!ItemStack.areItemStackTagsEqual(slot, stack)) {
						return false;
					}
				} else if (target instanceof List) {
					boolean matched = false;

					for (ItemStack stack : (List<ItemStack>) target) {
						matched = OreDictionary.itemMatches(stack, slot, false) &&
								ItemStack.areItemStackTagsEqual(slot, stack);
						if (matched)
							break;
					}

					if (!matched) {
						return false;
					}
				} else if (target == null && slot != null) {
					return false;
				}
			}
		}

		return true;
	}
}
