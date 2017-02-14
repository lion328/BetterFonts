package me.isuzutsuki.betterfonts;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.11.2")
@IFMLLoadingPlugin.Name(value = "BetterFonts")
@IFMLLoadingPlugin.TransformerExclusions(value = "me.isuzutsuki.betterfonts")
@IFMLLoadingPlugin.SortingIndex(value = 1001)
public class BetterFontsCore implements IFMLLoadingPlugin {

    public static final Logger BETTER_FONTS_LOGGER = LogManager.getLogger("BetterFonts");
    public static File location;

    @Override
    public String[] getASMTransformerClass() {
        return new String[] { BTFClassTransformer.class.getName() };
    }

    @Override
    public void injectData(Map<String, Object> data) {
        location = (File) data.get("coremodLocation");
    }

    @Override
    public String getModContainerClass() {
        return BTFDummyContainer.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
