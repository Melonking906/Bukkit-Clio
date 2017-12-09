package me.nonit.clio.operation;

import me.nonit.clio.Clio;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Scanner
{
    private Player player;

    public Scanner( Player player )
    {
        this.player = player;
    }

    public void scanForPlayers()
    {
        for( Player p : Bukkit.getOnlinePlayers() )
        {
            if( p != player && p.getWorld() == player.getWorld() && p.getLocation().distance( player.getLocation() ) < 100 )
            {
                if( p.hasPermission( "clio.scan.shield" ) )
                {
                    p.sendMessage( Clio.getPrefix() + "WARNING - Someone tried to scan you." );
                }
                else
                {
                    String pName;

                    if( p.getInventory().contains( Material.SPONGE ) )
                    {
                        pName = ChatColor.AQUA + p.getName();
                    }
                    else
                    {
                        pName = p.getName();
                    }

                    player.sendMessage( Clio.getPrefix() + "I have detected " + ChatColor.RED + pName + ChatColor.WHITE + " within 100 blocks." );
                }
            }
        }
    }
}
