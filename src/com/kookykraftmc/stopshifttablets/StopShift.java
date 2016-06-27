package com.kookykraftmc.stopshifttablets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static org.bukkit.event.inventory.InventoryAction.HOTBAR_MOVE_AND_READD;
import static org.bukkit.event.inventory.InventoryAction.HOTBAR_SWAP;

public class StopShift extends JavaPlugin implements Listener
{
    public static final Logger log = Bukkit.getLogger();
    public void onEnable()
    {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onShiftClick(InventoryClickEvent e)
    {
        /*
         * This is like the messiest thing I've ever written
         * I will make it better at some point!
         */
        if(e.getInventory() == null || e.getInventory().getTitle() == null) {
            return;
        }
        Player p = (Player) e.getWhoClicked();

        /**  Debug, ignore   */
//        if(e.getClick().isShiftClick()){
//            p.sendMessage("Title: " + e.getInventory().getTitle());
//            p.sendMessage("Name: " + e.getInventory().getName());
//            e.setCancelled(false);
//        }

        if(e.getInventory().getName().equals("Pedestal")
                || e.getInventory().getName().equalsIgnoreCase("Chest")
                || e.getInventory().getTitle().equalsIgnoreCase("Chest")) {
            if(e.getClick().isShiftClick())
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Bad cat!");
                p.getWorld().createExplosion(p.getLocation(), 0F, false);
                return;
            }
        }

        if(!e.getInventory().getTitle().equals("container.ee3:transmutationTablet")
                && !(e.getInventory().getName().equalsIgnoreCase("Chest"))
                && !(e.getInventory().getTitle().equalsIgnoreCase("Chest")))
            return;
        if(e.getClick().isShiftClick())
            log.warning(p.getName() + "Tried to shift click with transmutation tablets!");
        else if(e.getAction() == HOTBAR_SWAP || e.getAction() == HOTBAR_MOVE_AND_READD)
            log.warning(p.getName() + "Tried to use the number key transmutation tablet dupe!");
        else
            return;
        p.getWorld().createExplosion(p.getLocation(), 0F, false);
        p.sendMessage(ChatColor.RED + "Bad cat!");
        e.setCancelled(true);
    }
}
