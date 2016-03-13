package com.hea3ven.buildingbricks.core.materials.loader;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTBase;

import com.hea3ven.buildingbricks.core.materials.BlockDescription;
import com.hea3ven.buildingbricks.core.materials.Material;
import com.hea3ven.buildingbricks.core.materials.MaterialBlockRecipes.MaterialBlockRecipeBuilder;
import com.hea3ven.buildingbricks.core.materials.MaterialBlockType;
import com.hea3ven.buildingbricks.core.materials.StructureMaterial;

public class MaterialBuilderDyeMeta extends MaterialBuilder {
	private Material[] mats;

	public MaterialBuilderDyeMeta(String id) {
		mats = new Material[16];
		for (int i = 0; i < 16; i++) {
			String postfix = "_" + EnumDyeColor.byMetadata(i).getName();
			mats[i] = new Material(id + postfix);
		}
	}

	@Override
	public MaterialBuilder setStructureMaterial(StructureMaterial type) {
		for (Material mat : mats) {
			mat.setStructureMaterial(type);
		}
		return this;
	}

	@Override
	public MaterialBuilder setHardness(float hardness) {
		for (Material mat : mats) {
			mat.setHardness(hardness);
		}
		return this;
	}

	@Override
	public MaterialBuilder setResistance(float resistance) {
		for (Material mat : mats) {
			mat.setResistance(resistance);
		}
		return this;
	}

	@Override
	public MaterialBuilder setNormalHarvestMaterial(String normalHarvest) {
		int i = 0;
		for (Material mat : mats) {
			String postfix = "_" + EnumDyeColor.byMetadata(i++).getName();
			mat.setNormalHarvestMaterial(normalHarvest + postfix);
		}
		return this;
	}

	@Override
	public MaterialBuilder setSilkHarvestMaterial(String silkHarvest) {
		int i = 0;
		for (Material mat : mats) {
			String postfix = "_" + EnumDyeColor.byMetadata(i++).getName();
			mat.setSilkHarvestMaterial(silkHarvest + postfix);
		}
		return this;
	}

	@Override
	public void setTextures(ImmutableMap<String, String> textures) {
		int i = 0;
		for (Material mat : mats) {
			String postfix = "_" + EnumDyeColor.byMetadata(i++).getName();
			String baseTexture =
					(textures.containsKey("all") ? textures.get("all") : textures.get("side")) + postfix;
			for (Entry<String, String> entry : textures.entrySet()) {
				mat.setTexture(entry.getKey(), entry.getValue() + postfix);
			}
			if (!textures.containsKey("all"))
				mat.setTexture("all", baseTexture);
			if (!textures.containsKey("side"))
				mat.setTexture("side", baseTexture);
			if (!textures.containsKey("top"))
				mat.setTexture("top", baseTexture);
			if (!textures.containsKey("particle"))
				mat.setTexture("particle", baseTexture);
			if (!textures.containsKey("wall"))
				mat.setTexture("wall", baseTexture);
			if (!textures.containsKey("texture"))
				mat.setTexture("texture", baseTexture);
			if (!textures.containsKey("pane"))
				mat.setTexture("pane", baseTexture);
			if (!textures.containsKey("edge"))
				mat.setTexture("edge", baseTexture);
			if (!textures.containsKey("layer0"))
				mat.setTexture("layer0", baseTexture);
		}
	}

	@Override
	public void addBlock(MaterialBlockType type, String blockName, int metadata, Map<String, NBTBase> tags,
			List<MaterialBlockRecipeBuilder> recipes) {
		metadata = 0;
		for (Material mat : mats) {
			mat.addBlock(new BlockDescription(type, blockName, metadata++, tags, recipes));
		}
	}

	@Override
	public void addBlock(MaterialBlockType type, List<MaterialBlockRecipeBuilder> recipes) {
		for (Material mat : mats) {
			mat.addBlock(new BlockDescription(type, recipes));
		}
	}

	@Override
	public Material[] build() {
		return mats;
	}
}
