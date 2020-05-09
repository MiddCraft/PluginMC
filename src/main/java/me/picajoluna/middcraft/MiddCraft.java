package me.picajoluna.middcraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the MiddCraft plugin.
 */

public class MiddCraft extends JavaPlugin implements Listener {
    private static MiddCraft middcraft;

    public static MiddCraft getPluginInstance() {
        return middcraft;
    }

    public void onEnable() {
        middcraft = this;
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MiddCraft enabled!");
        this.saveDefaultConfig();
        this.registerEvents(
                new Chat(),
                new Blocks(),
                new Welcome()
        );
    }

    public void registerEvents(Listener... listeners) {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "MiddCraft disabled.");
    }
}