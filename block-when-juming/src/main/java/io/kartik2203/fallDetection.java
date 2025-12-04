package io.kartik2203;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class fallDetection implements Listener {

    private ArrayList<Location> froms = new ArrayList<>();


    @EventHandler
    public void onFall(PlayerMoveEvent playerMoveEvent){
        Location from = playerMoveEvent.getFrom();
        froms.add(from);
        Location to = playerMoveEvent.getTo();
        if (froms.size()<10) return;
        if (from.getY() != to.getY() && froms.getFirst().getY()-froms.getLast().getY()>0.01){
            World world = playerMoveEvent.getPlayer().getWorld();
            to.setY(to.getY()-1);
            world.setBlockData(to, BlockType.COBBLED_DEEPSLATE.createBlockData());
            froms.clear();
        }

    }

}
