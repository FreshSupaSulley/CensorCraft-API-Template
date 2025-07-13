package com.example.examplemod;

import io.github.freshsupasulley.censorcraft.api.CensorCraftPlugin;
import io.github.freshsupasulley.censorcraft.api.ForgeCensorCraftPlugin;
import io.github.freshsupasulley.censorcraft.api.events.EventRegistration;
import io.github.freshsupasulley.censorcraft.api.events.server.ServerConfigEvent;

@ForgeCensorCraftPlugin
public class MyCCPlugin implements CensorCraftPlugin {
	
	// Constructor must take no args!
	public MyCCPlugin()
	{
	}
	
	@Override
	public void registerEvents(EventRegistration registration)
	{
		registration.registerEvent(ServerConfigEvent.class, this::onServerConfig);
	}
	
	public void onServerConfig(ServerConfigEvent event)
	{
		event.registerPunishment(HungerPunishment.class);
	}
	
	@Override
	public String getPluginId()
	{
		return ExampleMod.MODID;
	}
}
