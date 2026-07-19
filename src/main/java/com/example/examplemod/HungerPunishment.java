package com.example.examplemod;

import io.github.freshsupasulley.censorcraft.api.punishments.Punishment;
import io.github.freshsupasulley.censorcraft.api.punishments.PunishmentJob;
import io.github.freshsupasulley.censorcraft.api.punishments.PunishmentParam;
import io.github.freshsupasulley.censorcraft.api.punishments.Trie;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Demo punishment that depletes the user's hunger bar.
 * 
 * <p>
 * Only works in survival and when not in peaceful mode.
 * </p>
 */
public class HungerPunishment extends Punishment {
	
	@Override
	public String getId()
	{
		// This overrides the default name that appears in the server config file
		return "hunger";
	}
	
	// Make this punishment enabled when first written to the config file
	@Override
	protected boolean isEnabledOnInit()
	{
		return true;
	}
	
	@Override
	protected void buildConfig()
	{
		// Don't use floats, at all. Use doubles (the config system doesn't like floats). Ints are fine too.
		// This is because the config system we use only recognizes doubles and will fail validation for floats
		define("exhaustion", 500, "The amount of food exhaustion to inflict onto the player");
	}
	
	@Override
	public <T> T getParam(PunishmentParam<T> param, T fallback)
	{
		if(param == PunishmentParam.TABOOS)
		{
			// The orElse logic here is to handle the case when "taboos" isn't set in your punishment config
			List<String> taboos = config.getOrElse(param.getKey(), new ArrayList<>());
			// Always add "corn" to the taboos along with whatever the server owner put down
			taboos.add("corn");
			return (T) taboos;
		}
		
		// fallback to parent behavior to fetch from disk
		return super.getParam(param, fallback);
	}
	
	@Override
	public @Nullable PunishmentJob punish(Object player, Set<String> taboos)
	{
		var sp = (ServerPlayer) player;
		// When retrieving data from the server config file, casting to the Number class is the best way to get values (for now)
		sp.causeFoodExhaustion(((Number) config.get("exhaustion")).floatValue());
		sp.sendSystemMessage(Component.literal("GET TROLLED"));
		// You can optionally return a PunishmentJob if your punishment takes an abhorrent amount of time to complete to distribute your code across multiple ticks
		return null;
	}
}
