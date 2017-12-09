package me.nonit.clio.operation;

import me.nonit.clio.Clio;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Give
{
    private Player player;
    private final Material[] materials = { Material.DIAMOND, Material.COOKED_BEEF, Material.BLAZE_ROD, Material.TNT, Material.GOLDEN_APPLE, Material.WEB, Material.EXP_BOTTLE };
    private String itemName;

    public Give( Player player )
    {
        this.player = player;
    }

    public boolean givePlayerItem()
    {
        Inventory inventory = player.getInventory();

        inventory.addItem( createItem() );

        player.sendMessage( Clio.getPrefix() + "I downloaded this for you, " + ChatColor.BLUE + itemName );

        return true;
    }

    private ItemStack createItem()
    {
        int i = (int)( Math.random() * this.materials.length );
        ItemStack stack = new ItemStack( materials[i] );

        ItemMeta meta = stack.getItemMeta();

        if( stack.getType().equals( Material.DIAMOND ) )
        {
            this.itemName = "Hyper Ammo";
            meta.setDisplayName( this.itemName );
        }
        else
        {
            this.itemName = stack.getType().name();
        }

        stack.setItemMeta( meta );

        stack.setAmount( ((int) (Math.random() * 32)) + 1 );

        return stack;
    }
}