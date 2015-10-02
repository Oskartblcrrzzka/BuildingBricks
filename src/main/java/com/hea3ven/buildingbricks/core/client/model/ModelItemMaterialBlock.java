package com.hea3ven.buildingbricks.core.client.model;

import java.util.HashMap;

import javax.vecmath.Vector3f;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;

import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartItemModel;
import net.minecraftforge.common.util.Constants.NBT;

@SuppressWarnings("deprecation")
public class ModelItemMaterialBlock extends DelegatedSmartModel implements ISmartItemModel {

	private static ItemCameraTransforms cameraTransforms = new ItemCameraTransforms(
			new ItemTransformVec3f(new Vector3f(-20, 135, 180),
					new Vector3f(0, 1.5f * 0.0625f, -2.75f * 0.0625f),
					new Vector3f(0.375f, 0.375f, 0.375f)),
			new ItemTransformVec3f(new Vector3f(0, 180, 0), new Vector3f(0, 0, 0),
					new Vector3f(1f, 1f, 1f)),
			new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1, 1, 1)),
			new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1, 1, 1)));

	private HashMap<String, ModelItemMaterialBlock> models;

	public ModelItemMaterialBlock() {
		super(null);

		models = new HashMap<String, ModelItemMaterialBlock>();
	}

	public ModelItemMaterialBlock(IFlexibleBakedModel delegate) {
		super(delegate);
	}

	public void put(String materialId, IFlexibleBakedModel model) {
		models.put(materialId, new ModelItemMaterialBlock(model));
	}

	@Override
	public IBakedModel handleItemState(ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("material", NBT.TAG_STRING))
			return models.get(stack.getTagCompound().getString("material"));
		else
			return this;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return cameraTransforms;
	}
}