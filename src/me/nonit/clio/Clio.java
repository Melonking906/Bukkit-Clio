package me.nonit.clio;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Clio extends JavaPlugin
{
    private static Economy econ = null;

    private static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.BLUE + "CLIO" + ChatColor.GRAY + "]" + ChatColor.WHITE + " ";

    @Override
    public void onEnable()
    {
        setupEconomy();
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents( new PlayerListener(this), this );
        pm.registerEvents( new CrackShotListener( this ), this );
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null)
        {
            econ = economyProvider.getProvider();
        }

        return ( econ != null);
    }

    public static Economy getEcon()
    {
        return econ;
    }
    public static String getPrefix()
    {
        return PREFIX;
    }
}
