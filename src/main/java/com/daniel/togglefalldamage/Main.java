package com.daniel.togglefalldamage;

import com.daniel.togglefalldamage.commands.ToggleFallDamageCommand;
import com.daniel.togglefalldamage.events.FallDamageEventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("togglefalldamage").setExecutor(new ToggleFallDamageCommand(this));
        Bukkit.getPluginManager().registerEvents(new FallDamageEventHandler(this), this);
    }
}
