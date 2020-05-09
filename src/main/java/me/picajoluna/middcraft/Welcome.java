package me.picajoluna.middcraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Class to handle playerJoin events:
 * - Chat messages (MOTD)
 * - Inventory edits (give compass)
 */

public class Welcome implements Listener {
    private MiddCraft middcraft = MiddCraft.getPluginInstance();

    private Configuration getConfig() {
        return (Configuration)(this.middcraft != null ? this.middcraft.getConfig() : new YamlConfiguration());
    }

    /**
     * Sends player a message on join, depending on the player's group. Messages are written in the config file.
     * Players inheriting permissions from group "build" (i.e. everyone, but group default)
     * receive the "motd-all" message. Those without receive the "motd-no-perms" message.
     */
    @EventHandler
    public void messageOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.sendPlayerMOTD(player);
    }

    public void sendPlayerMOTD(Player player) {
        String group = "build";
        String motdAll = this.getConfig().getString("motd-all");
        String noPermsMessage = this.getConfig().getString("motd-no-perms");
        if (!this.isPlayerInGroup(player, group)) {
            String motd = motdAll + "\n" + noPermsMessage;
            player.sendMessage(motd);
        } else {
            player.sendMessage(motdAll);
        }
    }

    /**
     * Returns a player's permissions group in format: "group.groupname" (e.g. "group.student")
     */
    public boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    /**
     * Gives a player a compass on join, if inventory not full & contains no compass.
     */
    @EventHandler
    public void defaultItemsOnJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();

        ItemStack compasses = new ItemStack(Material.COMPASS, 1);
        ItemMeta im = compasses.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Left-click to teleport" + ChatColor.GRAY + ", " + ChatColor.GREEN + "Right-click to go through blocks!");
        compasses.setItemMeta(im);

        /** player.getInventory().firstEmpty() returns -1 if there are no available slots in the inventory */
        if ((! inv.contains(Material.COMPASS)) && (inv.firstEmpty() != -1 )) {
            inv.setItem(inv.firstEmpty(), compasses);
        }
    }


    /**
     * Gives a player 'infinite' night-vision effect upon join.
     * The 'infinite' value is the max possible integer value, which is 2^31 - 1
     */
    @EventHandler
    public void potionEffectsoOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1)));
    }






}