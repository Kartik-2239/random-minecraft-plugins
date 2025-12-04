package org.tp.jumpDetector;

import io.papermc.paper.event.entity.EntityMoveEvent;
import java.util.Random;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public final class JumpDetector extends JavaPlugin implements Listener {

    public static final PotionEffectType[] GOOD_EFFECTS = {
            PotionEffectType.SPEED,
            PotionEffectType.HASTE,      // Haste
            PotionEffectType.STRENGTH,   // Strength
            PotionEffectType.JUMP_BOOST,
            PotionEffectType.REGENERATION,
            PotionEffectType.RESISTANCE,
            PotionEffectType.FIRE_RESISTANCE,
            PotionEffectType.WATER_BREATHING,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.HEALTH_BOOST,
            PotionEffectType.ABSORPTION,
            PotionEffectType.SATURATION,
            PotionEffectType.LUCK
    };


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onEntityMovement(PlayerJumpEvent jumpEvent){
        Player player = jumpEvent.getPlayer();
        Random rand = new Random();

        ArrayList<PotionEffect> effects =  new ArrayList<>();
        for (PotionEffectType eff:GOOD_EFFECTS){
            int duration = rand.nextInt(60,200);
            PotionEffect curEff = new PotionEffect(eff,duration,1,false,true,true);
            if (player.hasPotionEffect(eff)) return;
            effects.add(curEff);
        }

        player.addPotionEffect(effects.get(rand.nextInt(GOOD_EFFECTS.length)),true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
