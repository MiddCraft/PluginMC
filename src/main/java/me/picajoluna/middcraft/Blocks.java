package me.picajoluna.middcraft;

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
    public void playerDropsBlock(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("group.build")) {
            player.sendMessage(ChatColor.GRAY + "You can't drop items; use a chest to exchange items with other players.");
        } else {
            player.sendMessage("You can't drop blocks.");
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void blockDropsFromBlock(BlockDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockDropsFromDispenser(BlockDispenseEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void blockBroken(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getState() instanceof BlockInventoryHolder) {
        }

    }
}
