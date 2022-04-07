package net.timenation.allvsplayer

import eu.thesimplecloud.api.CloudAPI
import net.timenation.allvsplayer.commands.SettingsCommand
import net.timenation.allvsplayer.commands.StartCommand
import net.timenation.allvsplayer.commands.StopCommand
import net.timenation.allvsplayer.listener.AsyncPlayerChatListener
import net.timenation.allvsplayer.listener.InventoryClickListener
import net.timenation.allvsplayer.listener.PlayerJoinListener
import net.timenation.allvsplayer.manager.GameManager
import net.timenation.allvsplayer.manager.GameState
import net.timenation.timespigotapi.manager.color.colorapi.ColorAPI
import net.timenation.timespigotapi.manager.language.ConfigManager
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.PluginManager
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class AllVsPlayer : JavaPlugin() {

    var prefix : String
    var playerList : ArrayList<Player>
    var gameManager: GameManager
    var color : String
    var freeze : Boolean
    var pvp : Boolean

    override fun onEnable() {
        super.onEnable()
        val  pluginManager : PluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerJoinListener(), this)
        pluginManager.registerEvents(InventoryClickListener(), this)
        pluginManager.registerEvents(AsyncPlayerChatListener(), this)
        getCommand("settings")?.setExecutor(SettingsCommand())
        getCommand("start")?.setExecutor(StartCommand())
        getCommand("stop")?.setExecutor(StopCommand())

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, Runnable {
            when(gameManager.gameState) {
                GameState.CONFIGURATION -> Bukkit.getOnlinePlayers().forEach() { it.sendActionBar("${prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Aktuelle Konfiguration§8: ${ColorAPI.process("<SOLID:${color}>")}${Bukkit.getServer().maxPlayers} Spieler §8- ${ColorAPI.process("<SOLID:${color}>")}Map §l:D") }
                GameState.STARTING -> Bukkit.getOnlinePlayers().forEach() { if(it.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()) it.sendActionBar("${prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Informationen§8: ${ColorAPI.process("<SOLID:${color}>")}${Bukkit.getServer().onlinePlayers.size} Spieler §8- ${ColorAPI.process("<SOLID:${color}>")}Map §l:D §8- §7Nutze ${ColorAPI.process("<SOLID:${color}>")}§l/start §7zum starten§8§l!") }
                GameState.INGAME -> Bukkit.getOnlinePlayers().forEach() { it.sendActionBar("${prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Informationen§8: ${ColorAPI.process("<SOLID:${color}>")}${playerList.size} lebende Spieler §8- ${ColorAPI.process("<SOLID:${color}>")}Map §l:D") }
            }
        }, 0, 1)
    }

    init {
        instance = this
        color = "d12f2a"
        prefix = ColorAPI.process(ConfigManager("de").getString("api.allvsplayer.prefix"))
        playerList = ArrayList()
        gameManager = GameManager(Location(Bukkit.getWorld("world"), 0.5, 66.0, 0.5), GameState.CONFIGURATION)
        freeze = false
        pvp = false
    }

    companion object {
        @JvmStatic
        lateinit var instance : AllVsPlayer
            private set
    }
}