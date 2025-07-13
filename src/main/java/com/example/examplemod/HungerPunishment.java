package com.example.examplemod;

import io.github.freshsupasulley.censorcraft.api.punishments.Punishment;
import net.minecraft.server.level.ServerPlayer;

/**
 * Demo punishment that depletes the user's hunger bar.
 * 
 * <p>
 * Only works in survival and when not in peaceful mode.
 * </p>
 */
public class HungerPunishment extends Punishment {
	
	@Override
	public String getName()
	{
		// This overrides the default name that appears in the server config file
		return "hunger";
	}
	
	@Override
	protected void buildConfig()
	{
		// Don't use floats, at all. Use doubles (the config system doesn't like floats). Ints are fine too.
		// This is because the config system we use only recognizes doubles and will fail validation for floats
		define("exhaustion", 500, "The amount of food exhaustion to inflict onto the player");
	}
	
	@Override
	public void punish(Object serverPlayer)
	{
		// When retrieving data from the server config file, casting to the Number class is the best way to get values (for now)
		((ServerPlayer) serverPlayer).causeFoodExhaustion(((Number) config.get("exhaustion")).floatValue());
	}
}
