package net.timenation.allvsplayer.listener

import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.timespigotapi.manager.color.colorapi.ColorAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class AsyncPlayerChatListener : Listener {

    @EventHandler
    fun handleAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        if (event.player.hasMetadata("private_owner")) {
            event.format = "${ColorAPI.process("<SOLID:${AllVsPlayer.instance.color}>Target")} §8⋆ §7%1\$s §8§l» §7%2\$s"
        } else if (event.player.hasPermission("timenation.team")) {
            event.format = "§c§lTEAM §8⋆ §7%1\$s §8§l» §7%2\$s"
        } else {
            event.format = "§7Spieler §8⋆ §7%1\$s §8§l» §7%2\$s"
        }
    }
}