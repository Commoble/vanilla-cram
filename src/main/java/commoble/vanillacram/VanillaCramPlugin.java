package commoble.vanillacram;

import commoble.cram.api.AutoCramPlugin;
import commoble.cram.api.CramPlugin;
import commoble.cram.api.functions.CramRegistrator;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

// negative priority makes it easier for other plugins to override behaviours for their own pressure plates, etc
@AutoCramPlugin(priority=-1)
public class VanillaCramPlugin implements CramPlugin
{

	@Override
	public void register(CramRegistrator registry)
	{
		ForgeRegistries.BLOCKS.forEach(block -> doBlockRegistry(registry, block));
		
		VanillaCram.LOGGER.info("Loaded Cram Plugin for vanilla objects");
	}

	static void doBlockRegistry(CramRegistrator registry, Block block)
	{
		if (block instanceof AbstractPressurePlateBlock)
		{
			registry.getCramEntry(block)
				.setEntityCollisionBehavior(PressurePlateBehaviors::onEntityCollision)
				.setScheduledTickBehavior(PressurePlateBehaviors::onScheduledTick);
		}
	}
}
