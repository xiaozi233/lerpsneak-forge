package com.spxctreofficial.lerpsneak;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "lerpsneak", useMetadata=true, clientSideOnly = true)
public class LerpSneak {
    private static final Logger LOGGER = LogManager.getLogger();
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("LerpSneak is initialized");
    }
}
