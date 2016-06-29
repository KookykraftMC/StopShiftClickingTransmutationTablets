package com.kookykraftmc.stopshifttablets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.logging.Logger;

/**
 * Created by Michael on 6/28/2016.
 *
 */

public class Horse implements Listener {
    public static final Logger log = Bukkit.getLogger();
    public static StopShift plugin;

    public Horse() {
        super();
    }

    @EventHandler
    public void blockShiftClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.isInsideVehicle()) {
            try
                {
                    if (p.getVehicle().toString().contains("CraftHorse")) {
                        if (e.getClick().isShiftClick()) {
                            p.sendMessage(ChatColor.RED + "Bad Cat!" + ChatColor.GOLD + "Please don't shift click in your inventory while riding a horse!");
                            p.getWorld().createExplosion(p.getLocation(), 0F, false);
                            e.setCancelled(true);
                        }
                    }
                }
            catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }
}
