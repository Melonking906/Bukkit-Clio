package me.nonit.clio.operation;

import me.nonit.clio.Clio;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Decode
{
    private Player player;

    public Decode( Player player )
    {
        this.player = player;
    }

    public void clioDecoder( String message )
    {
        double d = Math.random() * 100;
        if (d < 15)
        {
            player.sendMessage( Clio.getPrefix() + "I decoded this message: " + ChatColor.BLUE + message );
        }
    }
}