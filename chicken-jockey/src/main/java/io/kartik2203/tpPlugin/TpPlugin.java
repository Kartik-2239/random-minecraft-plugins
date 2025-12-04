package io.kartik2203.tpPlugin;

import io.kartik2203.tpPlugin.EntityTracker;

import org.bukkit.event.EventHandler;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;

public class TpPlugin extends JavaPlugin implements Listener{

    private final EntityTracker tracker = new EntityTracker();


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("TpPlugin enabled!");
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        Entity entity = event.getEntity();
        Player player = Bukkit.getPlayer("Kartik22");

        if(!(entity instanceof LivingEntity)) return;
        if (player == null) return;
        if (player.getLocation().distance(entity.getLocation())>100) return;
        if (entity.getType() == EntityType.CHICKEN) return;
        if (tracker.isProcessed(entity)) {
            List<Entity>entities = player.getNearbyEntities(10,10,10);
            for (Entity e:entities){
                if (tracker.isProcessed(e)) return;
                if (entity.getType() == EntityType.CHICKEN) return;
                World world = entity.getWorld();

                tracker.markProcessed(e);

                Location location_ = e.getLocation();
                LivingEntity chicken_ = (LivingEntity) world.spawnEntity(location_, EntityType.CHICKEN);
                tracker.markProcessed(chicken_);
                chicken_.addPassenger(e);
            };
            return;
        }


        getLogger().info(String.format("A %s just spawned",entity.getType()));
        double entityHeight = entity.getHeight();
        tracker.markProcessed(entity);
        World world = entity.getWorld();
        Location location = entity.getLocation();
        LivingEntity chicken = (LivingEntity) world.spawnEntity(location, EntityType.CHICKEN);
        tracker.markProcessed(chicken);
        chicken.addPassenger(entity);
//        Bukkit.getScheduler().runTaskLater(this, entity::remove, 1L);
    }
}

