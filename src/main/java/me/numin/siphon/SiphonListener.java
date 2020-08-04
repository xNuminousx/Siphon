package me.numin.siphon;

import com.projectkorra.projectkorra.event.EntityBendingDeathEvent;
import org.bukkit.ChatColor;
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

        if (!Config.getEnabledWorlds().contains(world))
            return;

        if (killer.isDead() || killer.getHealth() >= 20)
            return;

        if (!killer.hasPermission("bendingsiphon.heal"))
            return;

        healKiller(killer, entity);
    }

    private void healKiller(Player killer, Entity entity) {
        double healAmount = toHealAmount(killer.getHealth(), entity instanceof Player ? Config.getPlayerKillAmount() : Config.getEntityKillAmount());
        killer.setHealth(killer.getHealth() + healAmount);
        if (Config.playAnimation()) killer.getWorld().spawnParticle(Particle.HEART, killer.getLocation().add(0, 1.7, 0), 1, 0, 0, 0);
        if (Config.canSendHealMessage()) killer.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "+" + healAmount / 2);
    }

    private double toHealAmount(double playerHealth, double targetHeal) {
        return playerHealth + targetHeal > 20 ? 20 - playerHealth : targetHeal;
    }
}
