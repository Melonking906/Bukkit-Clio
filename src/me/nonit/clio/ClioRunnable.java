package me.nonit.clio;

import me.nonit.clio.operation.Announce;
import me.nonit.clio.operation.AutoOperation;
import me.nonit.clio.operation.Give;
import me.nonit.clio.operation.Scanner;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ClioRunnable extends BukkitRunnable
{
    private Player player;

    public ClioRunnable( Player player )
    {
        this.player = player;
    }

    @Override
    public void run()
    {
        double d = Math.random();
        if (d < 0.5)
        {
            AutoOperation autoOperation = AutoOperation.randomOperation();

            switch( autoOperation )
            {
                case ANNOUNCE:
                    Announce announce = new Announce();
                    player.sendMessage( Clio.getPrefix() + announce.getAnnouncement() );
                    break;
                case GIVE:
                    Give give = new Give( player );
                    give.givePlayerItem();
                    break;
                case SCANNER:
                    Scanner scanner = new Scanner( player );
                    scanner.scanForPlayers();
                    break;
                default:
                    break;
            }
        }
    }
}
