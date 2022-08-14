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

        val player = if (args.isNotEmpty()) sender else plugin.server.getPlayer(args[0]) ?: return true
        plugin.server.onlinePlayers.filter { it.hasPermission("here.command.see") }.forEach {
            it.sendMessage("§7(§6${sender.name}§7) ${player.name}'s Location: (${sender.world.name}) ${"%,.1f".format(player.location.x)}, ${"%,.1f".format(player.location.y)}, ${"%,.1f".format(player.location.z)}")
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