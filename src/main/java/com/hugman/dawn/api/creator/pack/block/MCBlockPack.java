package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.BlockGetter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MCBlockPack extends Pack {
	private final Map<DyeColor, Block> blockMap = new HashMap<>();

	protected MCBlockPack(ModData modData, String name, BlockGetter getter, FabricBlockSettings settings, ItemGroup itemGroup, BlockCreator.Render render) {
		for(DyeColor color : DyeColor.values()) {
			blockMap.put(color, add(new BlockCreator.Builder(color.getName() + "_" + name, getter, settings.materialColor(color.getMaterialColor())).render(render).itemGroup(itemGroup), modData));
		}
	}

	public static class Builder implements PackBuilder {
		private final String name;
		private final BlockGetter getter;
		private final FabricBlockSettings settings;
		private ItemGroup itemGroup;
		private BlockCreator.Render render;

		/**
		 * Creates a creator pack containing blocks of 16 different colors.
		 *
		 * @param name     The name of the blocks. (ex: <code>bricks</code>)
		 * @param getter   The getter to use to build the blocks.
		 * @param settings The block settings.
		 */
		public Builder(String name, BlockGetter getter, FabricBlockSettings settings) {
			this.name = name;
			this.getter = getter;
			this.settings = settings;
		}

		public Builder setItemGroup(ItemGroup itemGroup) {
			this.itemGroup = itemGroup;
			return this;
		}

		public Builder setRender(BlockCreator.Render render) {
			this.render = render;
			return this;
		}

		public MCBlockPack build(ModData modData) {
			return new MCBlockPack(modData, name, getter, settings, itemGroup, render);
		}
	}

	public Block getBlock(DyeColor color) {
		return blockMap.get(color);
	}
}
