package com.kookykraftmc.stopshifttablets.commands;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.kookykraftmc.stopshifttablets.StopShift;

public class DebugCmd implements CommandExecutor
{
    Logger log = StopShift.log;

    @Override
    public boolean onCommand(CommandSender sender, Command whoEvenUsesThisParameter, String probablyNoone, String[] args)
    {
        if(args.length != 0)
        {
            if(!sender.hasPermission("stopshift."))
            {
                sender.sendMessage("You do not have permission to do this.");
                log.info(sender.getName() + "Tried to use /");
                return true;
            }
        }
        return false;
    }
    
}
