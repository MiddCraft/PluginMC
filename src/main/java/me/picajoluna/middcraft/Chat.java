
package me.picajoluna.middcraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Chat implements Listener {
    private MiddCraft middcraft = MiddCraft.getPluginInstance();

    private Configuration getConfig() {
        return (Configuration)(this.middcraft != null ? this.middcraft.getConfig() : new YamlConfiguration());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.sendPlayerMOTD(player);
    }

    @EventHandler
    public void denyChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String noChat = this.getConfig().getString("no-chat");
        if(!player.hasPermission("middcraft.chat")) {
            event.setCancelled(true);
            player.sendMessage(noChat);
        }
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

    public boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
