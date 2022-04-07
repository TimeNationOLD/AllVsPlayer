package net.timenation.allvsplayer.listener

import eu.thesimplecloud.api.CloudAPI
import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.allvsplayer.manager.GameState
import net.timenation.timespigotapi.TimeSpigotAPI
import net.timenation.timespigotapi.manager.color.colorapi.ColorAPI
import net.timenation.timespigotapi.manager.language.I18n
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerPreLoginEvent
import org.bukkit.metadata.FixedMetadataValue
import java.util.*

class PlayerJoinListener : Listener {

    @EventHandler
    fun handlePlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        if (player.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue() && AllVsPlayer.instance.gameManager.gameState == GameState.CONFIGURATION) {
            player.sendMessage(I18n.format(player, AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue())), "api.privateserver.messages.ingame.start", AllVsPlayer.instance.color))
            player.gameMode = GameMode.CREATIVE
            player.isOp = true
            player.sendTitle(AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue())), "§7Guck in den Chat§8§l!!")
            player.setMetadata("private_owner", FixedMetadataValue(AllVsPlayer.instance, true))
            Bukkit.getScheduler().runTaskLater(AllVsPlayer.instance, Runnable { TimeSpigotAPI.getInstance().tablistManager.registerRankTeam(player, ColorAPI.process("<SOLID:${AllVsPlayer.instance.color}>Target §8⋆ "), "", ChatColor.GRAY, 11) }, 10)
        } else if (player.hasPermission("timenation.team")) {
            Bukkit.getScheduler().runTaskLater(AllVsPlayer.instance, Runnable { TimeSpigotAPI.getInstance().tablistManager.registerRankTeam(player, "§c§lTeam §8⋆ ", "", ChatColor.GRAY, 12) }, 10)
        } else {
            Bukkit.getScheduler().runTaskLater(AllVsPlayer.instance, Runnable { TimeSpigotAPI.getInstance().tablistManager.registerRankTeam(player, "§7Spieler §8 ⋆", "", ChatColor.GRAY, 13) }, 10)
        }
        player.teleport(Location(Bukkit.getWorld("world"), 0.5, 66.0, 0.5))
        Bukkit.getOnlinePlayers().forEach { players: Player -> players.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}${ColorAPI.process("<SOLID:d12f2a>${player.name}")} §7hat das Spiel betreten§8.") }
    }

    @EventHandler
    fun handlePlayerPreLogin(event: PlayerPreLoginEvent) {
        if(AllVsPlayer.instance.gameManager.gameState == GameState.INGAME) {
            event.disallow(PlayerPreLoginEvent.Result.KICK_OTHER, "Ingame")
            return
        }
    }
}