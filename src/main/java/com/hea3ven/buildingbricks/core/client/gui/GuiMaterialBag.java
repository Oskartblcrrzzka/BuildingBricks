package com.hea3ven.buildingbricks.core.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.hea3ven.buildingbricks.core.items.ItemMaterialBag.InventoryMaterialBag;
import com.hea3ven.tools.commonutils.inventory.GenericContainer;

public class GuiMaterialBag extends GuiContainer {
	public static final int ID = 1;
	private static final ResourceLocation BG_RESOURCE =
			new ResourceLocation("buildingbricks:textures/gui/container/material_bag.png");

	public GuiMaterialBag(Container container) {
		super(container);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRendererObj.drawString(String.format("%.2f",
				((InventoryMaterialBag) inventorySlots.inventorySlots.get(0).inventory).getCurrentVolume() /
						1000f), 70, 64, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(BG_RESOURCE);

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
