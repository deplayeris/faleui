package mod.deplayer.faleui;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaleUI implements ModInitializer {

	public static final String MOD_ID = "faleui";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("---------------------This is faleUI, succeesfully loaded!---------------------");

		ModItems.initialize();
	}
}