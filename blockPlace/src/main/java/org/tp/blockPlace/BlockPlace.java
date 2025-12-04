package org.tp.blockPlace;
import org.bukkit.plugin.java.JavaPlugin;
import org.tp.blockPlace.BlockListener;

public final class BlockPlace extends JavaPlugin {
    BlockListener blockListener = new BlockListener();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(blockListener,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
