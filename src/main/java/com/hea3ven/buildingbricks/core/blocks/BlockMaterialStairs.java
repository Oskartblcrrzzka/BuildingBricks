package com.hea3ven.buildingbricks.core.blocks;

import java.util.List;

import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.hea3ven.buildingbricks.core.blocks.base.BlockMaterial;
import com.hea3ven.buildingbricks.core.lib.BlockDescription;
import com.hea3ven.buildingbricks.core.materials.Material;
import com.hea3ven.buildingbricks.core.materials.MaterialBlockType;
import com.hea3ven.buildingbricks.core.materials.MaterialRegistry;
import com.hea3ven.buildingbricks.core.materials.StructureMaterial;
import com.hea3ven.buildingbricks.core.tileentity.TileMaterial;

public class BlockMaterialStairs extends BlockBuildingBricksStairs implements BlockMaterial {

	public BlockMaterialStairs(StructureMaterial material) {
		super(material);
	}

	@Override
	protected BlockState createBlockState() {
		return TileMaterial.createBlockState(super.createBlockState());
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileMaterial();
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return TileMaterial.getExtendedState(state, world, pos);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state,
			EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		TileMaterial.getTile(worldIn, pos).setMaterial(TileMaterial.getStackMaterial(stack));
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) {
		ItemStack stack = super.getPickBlock(target, world, pos);
		TileMaterial.setStackMaterial(stack, TileMaterial.getTile(world, pos).getMaterial());
		return stack;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		TileMaterial.setStackMaterial(ret.get(0), TileMaterial.getTile(world, pos).getMaterial());
		return ret;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (Material mat : MaterialRegistry.getAll()) {
			BlockDescription blockDesc = mat.getBlock(MaterialBlockType.STAIRS);
			if (blockDesc != null && blockDesc.getBlock() == this) {
				ItemStack stack = new ItemStack(itemIn);
				TileMaterial.setStackMaterial(stack, mat);
				list.add(stack);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return blockLogic.getBlockColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state) {
		return blockLogic.getRenderColor(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return blockLogic.colorMultiplier(worldIn, pos, renderPass);
	}

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return blockLogic.getBlockLayer();
	}

	@Override
	public String getLocalizedName(Material mat) {
		if (StatCollector.canTranslate(getUnlocalizedName() + ".name"))
			return super.getLocalizedName();
		else
			return blockLogic.getLocalizedName(mat);
	}
}