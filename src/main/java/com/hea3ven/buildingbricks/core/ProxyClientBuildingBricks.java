package com.hea3ven.buildingbricks.core;

import net.minecraftforge.common.MinecraftForge;

import com.hea3ven.buildingbricks.core.client.ModelBakerBlockMaterial;
import com.hea3ven.buildingbricks.core.client.ModelBakerItemTrowel;
import com.hea3ven.buildingbricks.core.client.settings.TrowelKeyBindings;
import com.hea3ven.buildingbricks.core.eventhandlers.EventHandlerTrowelOverlay;

public class ProxyClientBuildingBricks extends ProxyCommonBuildingBricks {

	public ProxyClientBuildingBricks() {
		super();

		addModelBaker(new ModelBakerBlockMaterial());
		addModelBaker(new ModelBakerItemTrowel());
	}

	@Override
	public void onInitEvent() {
		super.onInitEvent();

		MinecraftForge.EVENT_BUS.register(new EventHandlerTrowelOverlay());

		TrowelKeyBindings.init();
	}
}
