package org.tp.blockRandomizer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class BlockRandomizer extends JavaPlugin implements Listener {

    HashMap<Material, Material> blockMap = new HashMap<>();

    List<Material> allMaterials = Arrays.asList(Material.values());

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockDropItemEvent event) {
        Block curBlock = event.getBlock();
        if (blockMap.containsKey(curBlock.getType())){
            Material toChangeTo = blockMap.get(curBlock.getType());
            curBlock.getWorld().dropItem(curBlock.getLocation(), new ItemStack(toChangeTo));
        }else {
            Random random = new Random();
            Material newMaterial = allMaterials.get(random.nextInt(allMaterials.size()));
            blockMap.put(curBlock.getType(),newMaterial);
            curBlock.getWorld().dropItem(curBlock.getLocation(), new ItemStack(newMaterial));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
