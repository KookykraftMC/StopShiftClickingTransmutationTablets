package com.kookykraftmc.stopshifttablets;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.event.inventory.InventoryAction.HOTBAR_MOVE_AND_READD;
import static org.bukkit.event.inventory.InventoryAction.HOTBAR_SWAP;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class StopShift extends JavaPlugin implements Listener
{
    static final Logger log = Bukkit.getLogger();
    public void onEnable()
    {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onShiftClick(InventoryClickEvent e)
    {
        if(e.getInventory() == null)
            return;
        if(!e.getInventory().getType().name().equals("CHEST"))
            return;
        if(!e.getClick().isShiftClick())
            return;
        if(!e.getInventory().getTitle().equals("container.ee3:transmutationTablet"))
            return;
        Player p = (Player) e.getWhoClicked();
        p.sendMessage(ChatColor.RED + "Do not shift click transmutation tablets!");
        Vector v = new Vector();
        v.setX(5.0);
        v.setZ(5.0);
        p.setVelocity(v);
        e.setCancelled(true);

    }
    public void onHotbarSwap(InventoryClickEvent e)
    {
        if(e.getInventory() == null)
            return;
        if(!e.getInventory().getType().name().equals("CHEST"))
            return;
        if(!e.getClick().isShiftClick())
            return;
        if(!e.getInventory().getTitle().equals("container.ee3:transmutationTablet"))
            return;
        if(e.getAction() == HOTBAR_SWAP || e.getAction() == HOTBAR_MOVE_AND_READD) {
            Player p = (Player) e.getWhoClicked();
            p.sendMessage(ChatColor.RED + "Bad cat!");
            e.setCancelled(true);
        }
    }
}
