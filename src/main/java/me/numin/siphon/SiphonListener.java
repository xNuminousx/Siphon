package me.numin.siphon;

import com.projectkorra.projectkorra.event.EntityBendingDeathEvent;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SiphonListener implements Listener {
    @EventHandler
    public void onDeath(EntityBendingDeathEvent event) {
        Entity entity = event.getEntity();
        Player killer = event.getAttacker();
        String world = killer.getWorld().getName();
        double killerHealth = killer.getHealth();

        if (!Config.getEnabledWorlds().contains(world))
            return;

        if (killerHealth >= 20)
            return;

        killer.getWorld().spawnParticle(Particle.HEART, killer.getLocation().add(0, 1.7, 0), 1, 0, 0, 0);
        if (entity instanceof Player)
            killer.setHealth(killerHealth + toHealAmount(killerHealth, Config.getPlayerKillAmount()));
        else
            killer.setHealth(killerHealth + toHealAmount(killerHealth, Config.getEntityKillAmount()));
    }

    private double toHealAmount(double playerHealth, double targetHeal) {
        return playerHealth + targetHeal > 20 ? 20 - playerHealth : targetHeal;
    }
}
