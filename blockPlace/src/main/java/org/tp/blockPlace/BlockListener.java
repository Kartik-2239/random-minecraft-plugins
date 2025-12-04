package org.tp.blockPlace;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.Random;

public class BlockListener implements Listener {
    @EventHandler
    public void bl(BlockPlaceEvent event){
        Random random = new Random();
        if (random.nextInt(100) < 26){
            event.getBlockPlaced().setBlockData(Material.LAVA.createBlockData());
        }
    }
    @EventHandler
    public void eggThrow(PlayerEggThrowEvent event){
        World world = event.getEgg().getWorld();
        EntityType entityType = EntityType.BLAZE;
        Entity blaze = world.spawnEntity(event.getEgg().getLocation(),entityType);
        event.getEgg().addPassenger(blaze);
    }
}
