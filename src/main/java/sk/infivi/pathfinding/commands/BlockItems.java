package sk.infivi.pathfinding.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.infivi.pathfinding.graph.NodeType;

import java.util.ArrayList;
import java.util.List;

public class BlockItems {

    public static ItemStack floorBlock;
    public static ItemStack wallBlock;
    public static ItemStack startBlock;
    public static ItemStack endBlock;
    public static ItemStack cornerBlock;

    //public static ArrayList<ItemStack> blockItems;

    static {

        // Base
        ItemStack baseBlock = new ItemStack(Material.STONE, 1);
        baseBlock.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);

        ItemMeta baseMeta = baseBlock.getItemMeta();
        baseMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        baseMeta.setDisplayName(ChatColor.GRAY + "base block");
        baseBlock.setItemMeta(baseMeta);


        // Floor block
        floorBlock = new ItemStack(baseBlock);
        floorBlock.setType(NodeType.NORMAL.block);

        ItemMeta floorBlockMeta = floorBlock.getItemMeta();
        floorBlockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Floor block");
        List<String> floorBlockLore = new ArrayList<>();
        floorBlockLore.add("Place to make a floor. Floors are a standard, walkable block.");
        floorBlockMeta.setLore(floorBlockLore);
        floorBlock.setItemMeta(floorBlockMeta);


        // Wall block
        wallBlock = new ItemStack(baseBlock);
        wallBlock.setType(NodeType.WALL.block);

        ItemMeta wallBlockMeta = wallBlock.getItemMeta();
        wallBlockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Wall block");
        List<String> wallBlockLore = new ArrayList<>();
        wallBlockLore.add("Place to make a wall. Walls can't be crossed.");
        wallBlockMeta.setLore(wallBlockLore);
        wallBlock.setItemMeta(wallBlockMeta);


        // Start block
        startBlock = new ItemStack(baseBlock);
        startBlock.setType(NodeType.START.block);

        ItemMeta StartBlockMeta = startBlock.getItemMeta();
        StartBlockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Start block");
        List<String> StartBlockLore = new ArrayList<>();
        StartBlockLore.add("Place to mark start. This is where the pathfinding algorithm starts.");
        StartBlockMeta.setLore(StartBlockLore);
        startBlock.setItemMeta(StartBlockMeta);


        // End block
        endBlock = new ItemStack(baseBlock);
        endBlock.setType(NodeType.END.block);

        ItemMeta EndBlockMeta = endBlock.getItemMeta();
        EndBlockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "End block");
        List<String> EndBlockLore = new ArrayList<>();
        EndBlockLore.add("Place to mark end. This is where the pathfinding algorithm ends.");
        EndBlockMeta.setLore(EndBlockLore);
        endBlock.setItemMeta(EndBlockMeta);


        // Corner block TODO: useless?
        cornerBlock = new ItemStack(baseBlock);
        cornerBlock.setType(Material.SEA_LANTERN);

        ItemMeta CornerBlockMeta = cornerBlock.getItemMeta();
        CornerBlockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Corner block");
        List<String> CornerBlockLore = new ArrayList<>();
        CornerBlockLore.add("Place to mark a corner. Two corners are used to construct the plane.");
        CornerBlockMeta.setLore(CornerBlockLore);
        cornerBlock.setItemMeta(CornerBlockMeta);

//        blockItems.add(floorBlock);
//        blockItems.add()
    }
}
