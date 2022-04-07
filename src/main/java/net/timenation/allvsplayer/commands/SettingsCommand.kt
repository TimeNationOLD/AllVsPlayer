package net.timenation.allvsplayer.commands

import eu.thesimplecloud.api.CloudAPI
import net.timenation.allvsplayer.AllVsPlayer
import net.timenation.allvsplayer.manager.GameState
import net.timenation.timespigotapi.manager.ItemManager
import net.timenation.timespigotapi.manager.language.I18n
import net.timenation.timespigotapi.manager.uuid.UUIDFetcher
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.util.*


class SettingsCommand : CommandExecutor {

    private var blackGlass: ItemStack = ItemManager(Material.BLACK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (AllVsPlayer.instance.gameManager.gameState == GameState.CONFIGURATION) {
                if(sender.hasPermission("timenation.team") || sender.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()) {
                    val inventory = Bukkit.createInventory(null, 9 * 3, I18n.format(sender, "${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§7Konfiguration"))

                    for (i in 0..8) {
                        inventory.setItem(i, blackGlass)
                    }
                    for (i in 18..26) {
                        inventory.setItem(i, blackGlass)
                    }

                    inventory.setItem(4, ItemManager(Material.PLAYER_HEAD, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Konfiguration").setLore(" §8» §7Spieler §8| §c${Bukkit.getServer().maxPlayers}", " §8» §7Map §8| §c:D").build())

                    if (sender.uniqueId == CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()) {
                        inventory.addItem(ItemManager(Material.PLAYER_HEAD, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}10 Spieler").setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWU4Y2M5YWQ3YTg4NmFjZTRiZjQ5MTA1MmQwODQyMzYwMmU1N2NkNDA5MmY1ZjM1ZWMwY2FhODVkOWQyNGJkIn19fQ==").build())
                        inventory.addItem(ItemManager(Material.PLAYER_HEAD, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}20 Spieler").setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I3ZDk5NmE5MDMwYzNhMDUxNjllNWRlYmY5YWJlZmRjMmNhOTEzYmZlMGU5ODhjZmI3ZjM1YjFkNjAwNWUyYyJ9fX0=").build())
                        inventory.addItem(ItemManager(Material.PLAYER_HEAD, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}50 Spieler").setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjVkOTc3NTk2ZWU3NWMyZGRhYWY0NjU2MjBiNjMyYjAwOGFjYzMxYWQzMTA1ZDY0MmViNmVjYWEzMmNkYmE5MSJ9fX0=").build())
                        inventory.addItem(ItemManager(Material.PLAYER_HEAD, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}100 Spieler").setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmJkMmY5MzQ3NmFiNjlmYWY1YTUxOWViNTgzMmRiODQxYzg1MjY2ZTAwMWRlNWIyNmU0MjdmNDFkOThlNWM3ZSJ9fX0=").build())

                        inventory.setItem(22, ItemManager(Material.GREEN_DYE).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}Fertig").build())

                        sender.openInventory(inventory)
                    } else if (sender.hasPermission("timenation.team")) {
                        for (i in 9..17) {
                            inventory.setItem(i, ItemManager(Material.BARRIER, 1).setDisplayName("${AllVsPlayer.instance.prefix.replace("Player", UUIDFetcher.getName(CloudAPI.instance.getCloudServiceManager().getCloudServiceByName(CloudAPI.instance.getThisSidesName())?.getProperty<UUID>("private_owner")?.getValue()))}§c§l✘").build())
                        }

                        sender.openInventory(inventory)
                    }
                }
            }
        }

        return true
    }
}