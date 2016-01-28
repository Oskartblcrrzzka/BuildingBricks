package com.hea3ven.buildingbricks.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.hea3ven.buildingbricks.core.blocks.BlockPortableLadder;
import com.hea3ven.buildingbricks.core.items.ItemMaterialBag;
import com.hea3ven.buildingbricks.core.items.ItemTrowel;
import com.hea3ven.buildingbricks.core.materials.mapping.IdMappingLoader;
import com.hea3ven.tools.bootstrap.Bootstrap;
import com.hea3ven.tools.commonutils.resources.ResourceScanner;

@Mod(modid = ModBuildingBricks.MODID, name = "Building Bricks", version = ModBuildingBricks.VERSION,
		dependencies = ModBuildingBricks.DEPENDENCIES,
		guiFactory = "com.hea3ven.buildingbricks.core.config.BuildingBricksConfigGuiFactory")
public class ModBuildingBricks {

	static {
		Bootstrap.init();
	}

	public static final String MODID = "buildingbricks";
	public static final String MODID_COMP_VANILLA = "buildingbrickscompatvanilla";
	public static final String VERSION = "@PROJECTVERSION@";
	public static final String DEPENDENCIES = "required-after:Forge@[@FORGEVERSION@,)";

	public static final Logger logger = LogManager.getLogger("BuildingBricks");

	@SidedProxy(serverSide = "com.hea3ven.buildingbricks.core.ProxyCommonBuildingBricks",
			clientSide = "com.hea3ven.buildingbricks.core.ProxyClientBuildingBricks")
	public static ProxyCommonBuildingBricks proxy;

	@SidedProxy(serverSide = "com.hea3ven.tools.commonutils.resources.ResourceScannerServer",
			clientSide = "com.hea3ven.tools.commonutils.resources.ResourceScannerClient")
	static ResourceScanner resScanner;

	public static ItemTrowel trowel;
	public static ItemMaterialBag materialBag;
	public static BlockPortableLadder portableLadder;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.onPreInitEvent(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		IdMappingLoader.save();

		proxy.onInitEvent(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.onPostInitEvent(event);
	}
}
