package net.timenation.allvsplayer.commands

import eu.thesimplecloud.api.CloudAPI
import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class StopCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            if(sender.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue() || sender.hasPermission("*")) {
                sender.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Du hast den Server §cgestoppt§8.")
                Bukkit.shutdown()
            }
        }

        return false
    }
}