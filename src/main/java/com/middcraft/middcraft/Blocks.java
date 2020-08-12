package com.middcraft.middcraft;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.BlockInventoryHolder;

/**
 * Class to handle block interactions.
 */

public class Blocks implements Listener {

    @EventHandler
    public void cancelPlayerItemDrops(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("group.build")) {
            player.sendMessage(ChatColor.GRAY + "You can't drop items; use a chest to exchange items with other players.");
        } else {
            player.sendMessage(ChatColor.GRAY + "You can't drop items.");
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void cancelBlockDrops(BlockDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void cancelDispenserDrops(BlockDispenseEvent event) {
        event.setCancelled(true);
    }

}
