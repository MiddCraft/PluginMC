
package me.picajoluna.middcraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Class to handle chat interactions.
 * See {@link Welcome} for chat messages sent on playerJoin.
 */

public class Chat implements Listener {

    /**
     * Players without the "middcraft.chat" permission cannot chat.
     * Group "build" has the permission, which everyone aside from "default" inherits,
     * so everybody but "default" (visitors" can chat).
     */
    @EventHandler
    public void restrictChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String noChat = MiddCraft.getPluginInstance().getConfig().getString("no-chat");
        if(!player.hasPermission("middcraft.chat")) {
            event.setCancelled(true);
            player.sendMessage(noChat);
        }
    }
}
