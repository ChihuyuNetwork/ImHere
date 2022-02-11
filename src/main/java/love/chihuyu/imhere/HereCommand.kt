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
            plugin.server.broadcastMessage("(${sender.name}) ${player.name}'s Location: ${player.location.x}, ${player.location.y}, ${player.location.z}")
        } else {
            plugin.server.broadcastMessage("(${sender.name}) ${sender.name}'s Location: ${sender.location.x}, ${sender.location.y}, ${sender.location.z}")
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return plugin.server.onlinePlayers.map { it.name }.toMutableList()
    }
}