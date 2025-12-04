package io.kartik2203;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import io.kartik2203.fallDetection;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public final class spiderMan extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
//        @NotNull Listener fallDetection = new fallDetection();
//        getServer().getPluginManager().registerEvents(fallDetection,this);
    }


    @EventHandler
    public void onEntityMovement(PlayerJumpEvent jumpEvent){
        World world = jumpEvent.getPlayer().getWorld();
        if (world.getEnvironment() == World.Environment.NETHER || world.getEnvironment() == World.Environment.THE_END){
            Player p = jumpEvent.getPlayer();
            long durationInTicks = 4;
            if (p.isSprinting()){
                durationInTicks = 10;
            }
            Bukkit.getScheduler().runTaskLater(this, () -> {
                Location loc = p.getLocation().clone();
                loc.setY(loc.getY()-0.5);
                world.setBlockData(loc, BlockType.COBBLED_DEEPSLATE.createBlockData());
            }, durationInTicks);
        }
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
