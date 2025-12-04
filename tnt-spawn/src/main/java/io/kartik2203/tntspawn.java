package io.kartik2203;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class tntspawn extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equalsIgnoreCase("tntspawner")) {
            if (sender instanceof Player player) {
                getServer().getScheduler().runTaskTimer(this, () -> {
                    if (player == null) return;
                    getLogger().info("Repeating...");
                    Location loc = player.getLocation().clone();
                    loc.setY(loc.getY() + 10);
                    ArrayList<Location> locs = new ArrayList<>();
                    for (int i=-2 ; i<=2; i++){
                        for (int k=-2 ; k<=2; k++){
                            Location newLoc = loc.clone();
                            newLoc.setX(newLoc.getX() + i*2);
                            newLoc.setZ(newLoc.getZ() + k*2);
                            locs.add(newLoc);
                        }
                    }
                    for (Location curLoc:locs){
                        loc.getWorld().spawnEntity(curLoc, EntityType.TNT);
                    }
                }, 0L, 100L);

            } else {
                sender.sendMessage("Only players can use this command.");
            }
            return true; // command handled
        }
        if (command.getName().equalsIgnoreCase("giveShovel")){
            if (sender instanceof Player player) {
                ItemStack item = new ItemStack(Material.GOLDEN_SHOVEL);
                item.addUnsafeEnchantment(Enchantment.KNOCKBACK,100);
                player.getInventory().addItem(item);
            }
        }
        return false; // not our command
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

