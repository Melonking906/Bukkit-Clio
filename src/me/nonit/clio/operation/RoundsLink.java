package me.nonit.clio.operation;

import com.shampaggon.crackshot.CSUtility;
import me.nonit.clio.Clio;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RoundsLink
{
    private Player player;

    public RoundsLink( Player player )
    {
        this.player = player;
    }

    public void notifyWin()
    {
        CSUtility csUtility = new CSUtility();

        csUtility.giveWeapon( player, "DisruptionCannon_Loki", 1 );

        player.sendMessage( Clio.getPrefix() + "We won! I loaded this " + ChatColor.BLUE + "Loki 12" + ChatColor.WHITE + " for you :3");
    }

    public void notifyLoose()
    {
        player.sendMessage( Clio.getPrefix() + "Come on! We can win next round!" );
    }
}