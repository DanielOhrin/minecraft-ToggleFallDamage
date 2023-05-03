package com.daniel.togglefalldamage.events;

import com.daniel.togglefalldamage.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class FallDamageEventHandler implements Listener {
    private final Main MAIN;
    public FallDamageEventHandler(Main main) {
        MAIN = main;
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player)e.getEntity();
        UUID playerUUID = p.getPlayer().getUniqueId();
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (MAIN.getConfig().isSet("players." + playerUUID + ".falldamage")) {
                if (!MAIN.getConfig().getBoolean("players." + playerUUID + ".falldamage")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
