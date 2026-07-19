package com.example.examplemod;

import io.github.freshsupasulley.censorcraft.api.CensorCraftPlugin;
import io.github.freshsupasulley.censorcraft.api.ForgeCensorCraftPlugin;
import io.github.freshsupasulley.censorcraft.api.events.PluginRegistration;
import io.github.freshsupasulley.censorcraft.api.events.client.SendTranscriptionEvent;

@ForgeCensorCraftPlugin
public class MyCCPlugin implements CensorCraftPlugin {
	
	// Constructor must take no args!
	public MyCCPlugin()
	{
	}
	
	@Override
	public void register(PluginRegistration registration)
	{
		// Register punishments
		registration.registerPunishment(HungerPunishment.class);
		// Hook into other fun events
		registration.registerEvent(SendTranscriptionEvent.class, this::sendTranscriptionEvent);
	}
	
	// You are on the client-side with this event
	public void sendTranscriptionEvent(SendTranscriptionEvent event)
	{
		if(event.getTranscription().toLowerCase().contains("corn"))
		{
			ExampleMod.LOGGER.info("Corn was spoken on the client!!! Get ready to die!!");
		}
		else if(event.getTranscription().toLowerCase().contains("green"))
		{
			ExampleMod.LOGGER.info("damn... that's my fav color. Can't send this packet no matter what :(. Cancelled: {}, can cancel: {}", event.cancel(), event.isCancellable());
		}
	}
	
	@Override
	public String getPluginId()
	{
		return ExampleMod.MODID;
	}
}
