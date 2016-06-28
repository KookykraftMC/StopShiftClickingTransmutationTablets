package com.kookykraftmc.stopshifttablets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static org.bukkit.event.inventory.InventoryAction.HOTBAR_MOVE_AND_READD;
import static org.bukkit.event.inventory.InventoryAction.HOTBAR_SWAP;

public class StopShift extends JavaPlugin implements Listener {
    public static final Logger log = Bukkit.getLogger();

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    /**
     * Debug method to get entity names of mobs hit.
     *
     * @param e Entity(ies)
     */
    @EventHandler
    public void onEntityInteract(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && ((Player) e.getDamager()).hasPermission("stopshift.debug")) {
            Player p = (Player) e.getDamager();
            p.sendMessage(ChatColor.GOLD + e.getEntity().toString());
            p.sendMessage(ChatColor.RED + e.getEntityType().toString());

        }
    }

    public StopShift() {
        super();
    }

    /**
     *
     * @param e ClickEvent base
     */
    @EventHandler
    public void onShiftClick(InventoryClickEvent e) {

        if (e.getInventory() == null || e.getInventory().getTitle() == null) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equals("Pedestal")) {
            if (e.getClick().isShiftClick()) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Bad cat!");
                p.getWorld().createExplosion(p.getLocation(), 0F, false);
                return;
            }
        }

        if (!e.getInventory().getTitle().equals("container.ee3:transmutationTablet"))
            return;
        if (e.getClick().isShiftClick())
            log.warning(p.getName() + "Tried to shift click with transmutation tablets!");
        else if (e.getAction() == HOTBAR_SWAP || e.getAction() == HOTBAR_MOVE_AND_READD)
            log.warning(p.getName() + "Tried to use the number key transmutation tablet dupe!");
        else
            return;
        p.getWorld().createExplosion(p.getLocation(), 0F, false);
        p.sendMessage(ChatColor.RED + "Bad cat!");
        e.setCancelled(true);
    }
}
