package net.timenation.allvsplayer.commands

import eu.thesimplecloud.api.CloudAPI
import eu.thesimplecloud.api.service.ServiceState
import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.allvsplayer.manager.GameState
import net.timenation.timespigotapi.manager.color.colorapi.ColorAPI
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class StartCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player && AllVsPlayer.instance.gameManager.gameState == GameState.STARTING && sender.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()) {
            var player : Player = sender

            Bukkit.getOnlinePlayers().forEach() {
                it.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Das Spiel startet nun§8! ${ColorAPI.process("<GRADIENT:ffb52b>§lTimeNation</GRADIENT:ffca2b>")} §7und §4§l${UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue())} §7wünschen euch viel Spaß§8! §4❤")
                if(it != player) {
                    AllVsPlayer.instance.playerList.add(it)
                }
                it.teleport(AllVsPlayer.instance.gameManager.spawnLocation)
            }
            AllVsPlayer.instance.gameManager.gameState = GameState.INGAME;
            AllVsPlayer.instance.pvp = true
            CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.setState(ServiceState.INVISIBLE)
        }

        return false
    }
}