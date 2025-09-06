package me.RafaelAulerDeMeloAraujo.Listeners;


import org.bukkit.Bukkit;

import net.wavemc.core.util.UpdateEvent;



public class UpdateScheduler implements Runnable {

    private int currentSeconds;

    @Override
    public void run() {
        currentSeconds++;
        Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.SEGUNDO));

        if (currentSeconds % 60 == 0) {
            Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.MINUTO));
        }

        if (currentSeconds % 3600 == 0) {
            Bukkit.getPluginManager().callEvent(new UpdateEvent(UpdateEvent.UpdateType.HORA));
        }
    }

}
