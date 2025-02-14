package me.RafaelAulerDeMeloAraujo.Listeners;


import org.bukkit.Bukkit;


public class UpdateScheduler implements Runnable {

    private int currentSeconds;

    @Override
    public void run() {
        currentSeconds++;

        Bukkit.getPluginManager().callEvent(new ServerTimerEvent(currentSeconds));

    }
}
