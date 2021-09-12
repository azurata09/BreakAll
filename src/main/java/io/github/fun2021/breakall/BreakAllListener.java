package io.github.fun2021.breakall;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class BreakAllListener implements Listener {

    private final BreakAllAPI api;

    public BreakAllListener() {
        BreakAll plugin = BreakAll.getInstance();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        api = plugin.getAPI();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if(!player.isSneaking()) return;
        BlockType blockType = api.getBlockType(event.getBlock());
        ItemType itemType = api.getItemType(mainHand);
        if(
                (blockType == BlockType.WOOD && itemType == ItemType.AXE) ||
                        (blockType == BlockType.ORE && itemType == ItemType.PICKAXE)
                        // (blockType == BlockType.DIRT && itemType == ItemType.SHOVEL)
        ) {
            Set<Block> neighbors = api.getNeighbor(event.getBlock());
            neighbors.forEach(b -> b.breakNaturally());
        }
    }
}
