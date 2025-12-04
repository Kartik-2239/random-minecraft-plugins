package org.tp.piglinTrading;

import org.tp.piglinTrading.getRandomBlock.GetRandomBlock;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.util.Vector;
import org.bukkit.Bukkit;

import java.util.*;

public final class PiglinTrading extends JavaPlugin implements Listener {
    ArrayList<Player> players = new ArrayList<>();
    GetRandomBlock getRandomBlock = new GetRandomBlock();
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);
        players.addAll(Bukkit.getOnlinePlayers());
    }

    @EventHandler
    public void onPiglinTrade(PiglinBarterEvent piglinBarterEvent){
        HashMap<Player, Double> distances = new HashMap<>();

        List<ItemStack> outcome =  piglinBarterEvent.getOutcome();

        Entity piglin = piglinBarterEvent.getEntity();
        for (Player p : players){
            distances.put(p,p.getLocation().distance(piglin.getLocation()));
        }
        outcome.clear();
        ItemStack item;
        try{
            item = new ItemStack(getRandomBlock.getRandomMaterial());
        }catch (Exception e){
            item = new ItemStack(getRandomBlock.getSureShotMaterial());
        }
        Item dropped = piglin.getWorld().dropItem(piglin.getLocation().add(0,1,0), item);
//        Player p = Bukkit.getPlayer("Kartik22");
        Player p = null;
        for (Player player: Bukkit.getOnlinePlayers()){
            if (player.getLocation().distance(piglin.getLocation())<50){
                p = player;
            }
        }
        if (p==null) {
            p = (new ArrayList<>(Bukkit.getOnlinePlayers())).getFirst();
        };
        Vector dir = p.getLocation().toVector()
                .subtract(piglin.getLocation().toVector())
                .normalize()
                .multiply(0.5);
        dropped.setVelocity(dir);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
