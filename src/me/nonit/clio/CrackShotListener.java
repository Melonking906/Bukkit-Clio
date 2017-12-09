package me.nonit.clio;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class CrackShotListener implements Listener
{
    private Clio plugin;
    private List<Player> onShootMessagedPlayers = new ArrayList<Player>();
    private final List<EntityType> notifyEntities = new ArrayList<EntityType>();
    private static Economy econ = Clio.getEcon();;

    public CrackShotListener( Clio plugin )
    {
        this.plugin = plugin;

        notifyEntities.add( EntityType.ZOMBIE );
        notifyEntities.add( EntityType.GIANT );
        notifyEntities.add( EntityType.SPIDER );
        notifyEntities.add( EntityType.SKELETON );
        notifyEntities.add( EntityType.CREEPER );
        notifyEntities.add( EntityType.PLAYER );
        notifyEntities.add( EntityType.VILLAGER );
        notifyEntities.add( EntityType.IRON_GOLEM );
        notifyEntities.add( EntityType.SNOWMAN );
        notifyEntities.add( EntityType.MUSHROOM_COW );
        notifyEntities.add( EntityType.WOLF );
        notifyEntities.add( EntityType.SQUID );
        notifyEntities.add( EntityType.CHICKEN );
        notifyEntities.add( EntityType.COW );
        notifyEntities.add( EntityType.SHEEP );
        notifyEntities.add( EntityType.PIG );
        notifyEntities.add( EntityType.WITCH );
        notifyEntities.add( EntityType.WITHER );
        notifyEntities.add( EntityType.ENDER_DRAGON );
        notifyEntities.add( EntityType.MAGMA_CUBE );
        notifyEntities.add( EntityType.BLAZE );
        notifyEntities.add( EntityType.SILVERFISH );
        notifyEntities.add( EntityType.CAVE_SPIDER );
        notifyEntities.add( EntityType.ENDERMAN );
        notifyEntities.add( EntityType.PIG_ZOMBIE );
        notifyEntities.add( EntityType.GHAST );
        notifyEntities.add( EntityType.SLIME );
    }

    @EventHandler
    public void onShoot( WeaponShootEvent event )
    {
        Player shooter = event.getPlayer();

        int[] shooterLocation = new int[3];
        shooterLocation[0] = (int) shooter.getLocation().getX();
        shooterLocation[1] = (int) shooter.getLocation().getY();
        shooterLocation[2] = (int) shooter.getLocation().getZ();

        for( final Player player : plugin.getServer().getOnlinePlayers() )
        {
            if( ! onShootMessagedPlayers.contains( player ) && ! player.equals( event.getPlayer() ) )
            {
                player.sendMessage( Clio.getPrefix() + "I have detected gun fire at: " + ChatColor.BLUE + shooterLocation[0] + "x, " + shooterLocation[1] + "y, " + shooterLocation[2] + "z!" );

                onShootMessagedPlayers.add( player );

                plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
                {
                    @Override
                    public void run()
                    {
                        onShootMessagedPlayers.remove( player );
                    }
                }, 400L );
            }
        }
    }

    @EventHandler
    public void onHit( WeaponDamageEntityEvent event )
    {
        Player player = event.getPlayer();
        Entity victim = event.getVictim();
        EntityType ammo = event.getDamager().getType();
        final Double reward = 2.0;

        if( ammo.equals( EntityType.SNOWBALL ) || ammo.equals( EntityType.EGG ) || ammo.equals( EntityType.ARROW ) )
        {
            if( notifyEntities.contains( victim.getType() ) )
            {
                String name;

                if( victim.getType().equals( EntityType.PLAYER ) )
                {
                    name = Bukkit.getPlayer( victim.getUniqueId() ).getName();
                }
                else
                {
                    name = victim.getType().getName();
                }

                if( event.isHeadshot() )
                {
                    player.sendMessage( Clio.getPrefix() + "Headshot on " + name + "! MeloCo payed " + reward + " " + econ.currencyNamePlural() );
                    econ.depositPlayer( Bukkit.getOfflinePlayer( player.getUniqueId() ), reward );
                    Bukkit.getServer().dispatchCommand( Bukkit.getConsoleSender(), "round updatescore " + player.getName() + " 50");
                }

                if( event.isCritical() )
                {
                    player.sendMessage( Clio.getPrefix() + "Critical hit on " + name + "! MeloCo payed " + reward + " " + econ.currencyNamePlural() );
                    econ.depositPlayer( Bukkit.getOfflinePlayer( player.getUniqueId() ), reward );
                    Bukkit.getServer().dispatchCommand( Bukkit.getConsoleSender(), "round updatescore " + player.getName() + " 50");
                }

                if( victim.getType().equals( EntityType.PIG ) )
                {
                    player.sendMessage( Clio.getPrefix() + "Aww, you hit a piggy :(" );
                }

                if( victim.getType().equals( EntityType.CHICKEN ) )
                {
                    player.sendMessage( Clio.getPrefix() + "DIE CHICKEN!" );
                }
            }
        }
    }
}