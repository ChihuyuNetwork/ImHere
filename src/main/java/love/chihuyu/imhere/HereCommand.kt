package love.chihuyu.imhere

import love.chihuyu.imhere.ImHere.Companion.plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

object HereCommand: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player || !sender.hasPermission("here.command.here")) return true

        if (args.isNotEmpty()) {
            val player = plugin.server.getPlayer(args[0]) ?: return true
            plugin.server.onlinePlayers.forEach {
                it.sendMessage("§7(§6${sender.name}§7) ${player.name}'s Location: ${"%,.1f".format(player.location.x)}, ${"%,.1f".format(player.location.y)}, ${"%,.1f".format(player.location.z)}")
            }
        } else {
            plugin.server.onlinePlayers.forEach {
                it.sendMessage("§7(§6${sender.name}§7) ${sender.name}'s Location: ${"%,.1f".format(sender.location.x)}, ${"%,.1f".format(sender.location.y)}, ${"%,.1f".format(sender.location.z)}")
            }
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return if (args.size == 1) plugin.server.onlinePlayers.map { it.name }.toMutableList() else mutableListOf()
    }
}