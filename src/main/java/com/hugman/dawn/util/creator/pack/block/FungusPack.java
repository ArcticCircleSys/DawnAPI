package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.object.block.FungusBlock;
import com.hugman.dawn.util.creator.block.BlockCreator.Builder;
import com.hugman.dawn.util.creator.block.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusPack extends PottedPlantPack {
	/**
	 * Creates an entry pack containing a fungus entry and its potted variant.
	 *
	 * @param suffix   The suffix of the fungus plant.
	 * @param supplier The supplier for the huge fungus feature.
	 */
	public FungusPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier) {
		super(new Builder(suffix + "_fungus", new FungusBlock(BlockSettings.FUNGUS, supplier)));
	}

	public Block getFungus() {
		return getPlant();
	}
}