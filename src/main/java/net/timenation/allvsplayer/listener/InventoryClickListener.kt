package net.timenation.allvsplayer.listener

import eu.thesimplecloud.api.CloudAPI
import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.allvsplayer.manager.GameManager
import net.timenation.allvsplayer.manager.GameState
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import java.util.*

class InventoryClickListener : Listener {

    @EventHandler
    fun handleInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.view.title == "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Konfiguration") {
            event.isCancelled = true

            println(event.currentItem!!.itemMeta.displayName.replace("§", "$"))
            println(AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue())).replace("§", "$"))

            when (event.currentItem!!.itemMeta.displayName.toUpperCase()) {
                "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}10 Spieler".toUpperCase() -> {
                    Bukkit.getServer().maxPlayers = 10
                    player.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Maximale Spielerzahl §8»» §c10")
                }
                "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}20 Spieler".toUpperCase() -> {
                    Bukkit.getServer().maxPlayers = 20
                    player.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Maximale Spielerzahl §8»» §c20")
                }
                "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}50 Spieler".toUpperCase() -> {
                    Bukkit.getServer().maxPlayers = 50
                    player.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Maximale Spielerzahl §8»» §c50")
                }
                "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}100 Spieler".toUpperCase() -> {
                    Bukkit.getServer().maxPlayers = 100
                    player.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Maximale Spielerzahl §8»» §c100")
                }
                "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Fertig".toUpperCase() -> {
                    AllVsPlayer.instance.gameManager = GameManager(Location(Bukkit.getWorld("world"), 0.5, 66.0, 0.5), GameState.STARTING)
                    player.sendMessage("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Nächste Phase §8»» §cSTARTING §8(§7Warten auf alle Spieler§8)")
                    player.closeInventory()
                }
            }
        }
    }
}