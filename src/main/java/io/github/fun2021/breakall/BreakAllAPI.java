package io.github.fun2021.breakall;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class BreakAllAPI {

    public BreakAllAPI() {

    }

    public ItemType getItemType(ItemStack itemStack) {
        String itemName = itemStack.getType().toString();
        if(itemName.contains("_AXE")) {
            return ItemType.AXE;
        } else if(itemName.contains("_PICKAXE")) {
            return ItemType.PICKAXE;
        } else if(itemName.contains("_SHOVEL")) {
            return ItemType.SHOVEL;
        } else {
            return ItemType.OTHERWISE;
        }
    }

    public BlockType getBlockType(Block block) {
        String blockName = block.getType().toString();
        if(blockName.contains("_LOG")) {
            return BlockType.WOOD;
        } else if(blockName.contains("_ORE")) {
            return BlockType.ORE;
        } else if(Arrays.asList("GRASS_BLOCK", "DIRT", "COARSE_DIRT", "SOUL_SOIL").contains(blockName)) {
            return BlockType.DIRT;
        } else {
            return BlockType.OTHERWISE;
        }
    }

    public Set<Block> getNeighbor(Block block) {
        Set<Block> searched = new HashSet<>();
        Set<Block> results = new HashSet<>();

        World world = block.getWorld();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();

        Queue<Block> blockQueue = new ArrayDeque<>(Arrays.asList(
                world.getBlockAt(blockX + 1, blockY, blockZ),
                world.getBlockAt(blockX - 1, blockY, blockZ),
                world.getBlockAt(blockX, blockY + 1, blockZ),
                world.getBlockAt(blockX, blockY - 1, blockZ),
                world.getBlockAt(blockX, blockY, blockZ + 1),
                world.getBlockAt(blockX, blockY, blockZ - 1)
        ));

        while (!blockQueue.isEmpty()) {
            Block block_ = blockQueue.poll();
            if(searched.contains(block_)) continue;
            searched.add(block_);
            if(block.getType() != block_.getType()) continue;
            results.add(block_);

            blockX = block_.getX();
            blockY = block_.getY();
            blockZ = block_.getZ();
            blockQueue.addAll(Arrays.asList(
                    world.getBlockAt(blockX + 1, blockY, blockZ),
                    world.getBlockAt(blockX - 1, blockY, blockZ),
                    world.getBlockAt(blockX, blockY + 1, blockZ),
                    world.getBlockAt(blockX, blockY - 1, blockZ),
                    world.getBlockAt(blockX, blockY, blockZ + 1),
                    world.getBlockAt(blockX, blockY, blockZ - 1)
            ));
        }
        return results;
    }
}
