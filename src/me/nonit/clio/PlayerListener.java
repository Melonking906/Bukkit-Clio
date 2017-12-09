package me.nonit.clio;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerListener implements Listener
{
    private Clio plugin;
    private static Economy econ;

    public PlayerListener( Clio instance )
    {
        plugin = instance;
        econ = Clio.getEcon();
    }

    @EventHandler
    public void onJoin( PlayerJoinEvent event )
    {
        Player player = event.getPlayer();
        ClioRunnable clioRunnable = new ClioRunnable( player );

        String version = plugin.getDescription().getVersion();
        player.sendMessage( Clio.getPrefix() + ChatColor.BLUE + "BOOTING... CLIO OS v" + version + "... DONE" );
        player.sendMessage( Clio.getPrefix() + "Hi there, I am Clio your " + ChatColor.RED + "Melo" + ChatColor.DARK_GRAY + "Co" + ChatColor.WHITE + " assistant." );
        player.sendMessage( Clio.getPrefix() + "I will report in soon..." );

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask( plugin, clioRunnable, 2400L, 2400L ); // Runs every 2 minutes.
    }

    @EventHandler
    public void onRespawn( PlayerRespawnEvent event )
    {
        event.getPlayer().sendMessage( Clio.getPrefix() + "Cloning complete, you have been restored!" );
    }

    @EventHandler
    public void onSneak( PlayerToggleSneakEvent event )
    {
        Player player = event.getPlayer();

        if( event.isSneaking() )
        {
            player.sendMessage( Clio.getPrefix() + "Cloaking Shield Activated" );
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        Player killed = event.getEntity();

        if( event.getEntity().getKiller() != null )
        {
            Player killer = event.getEntity().getKiller();
            killer.sendMessage( Clio.getPrefix() + "You terminated " + ChatColor.BLUE + killed.getName() + ChatColor.WHITE + "!" );
            econ.depositPlayer( killer, 5D );
            killed.sendMessage( Clio.getPrefix() + "You were terminated by " + ChatColor.BLUE + killer.getName() + ChatColor.WHITE + "!" );
            econ.withdrawPlayer( killed, 5D );
        }
    }
}