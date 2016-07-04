package com.kookykraftmc.stopshifttablets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.logging.Logger;

import static org.bukkit.event.inventory.InventoryAction.HOTBAR_SWAP;

public class StopShift extends JavaPlugin implements Listener
{
    public static final Logger log = Bukkit.getLogger();
    private static final HashSet<String> restrictedList = new HashSet<String>();

    public void onEnable()
    {
        File cfg = new File(getDataFolder(), "config.yml");
        if (!cfg.exists())
            saveDefaultConfig();
        loadCfg();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new Horse(), this);
    }
//
//    /**
//     * Debug method to get entity names of mobs hit.
//     *0
//     * @param e Entity(ies)
//     */
//    @EventHandler
//    public void onEntityInteract(EntityDamageByEntityEvent e) {
//        if (e.getDamager() instanceof Player && ((Player) e.getDamager()).hasPermission("stopshift.debug")) {
//            Player p = (Player) e.getDamager();
//            p.sendMessage(ChatColor.GOLD + e.getEntity().toString());
//            p.sendMessage(ChatColor.RED + e.getEntityType().toString());
//
//        }
//    }

    public StopShift()
    {
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

        if(restrictedList.contains(e.getInventory().getTitle()))
        {
            if(!e.getClick().isShiftClick() && e.getAction() != HOTBAR_SWAP)
                return;
            log.info("[StopShift]" + p.getName() + " tried to " + e.getClick().name() + " on a " + e.getInventory().getTitle());
            p.getWorld().createExplosion(p.getLocation(), 0F, false);
            p.sendMessage(ChatColor.RED + this.getConfig().getString("DenyMsg"));
            e.setCancelled(true);
        }
    }
    
    void loadCfg()
    {
        restrictedList.removeAll(restrictedList);
        for(String title:this.getConfig().getStringList("RestrictedInventories"))
        {
            restrictedList.add(title);
        }
    }
}
