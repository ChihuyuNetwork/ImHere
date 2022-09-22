package love.chihuyu.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.PlayerArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.ImHere.Companion.plugin
import org.bukkit.ChatColor

object CommandHere {

    val main = CommandAPICommand("here")
        .withPermission("imhere.here")
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val gray = ChatColor.GRAY
            val gold = ChatColor.GOLD
            plugin.server.broadcast(
                "$gray($gold${sender.name}$gray) (${sender.world.name}) ${sender.location.x.toInt()} ${sender.location.y.toInt()} ${sender.location.z.toInt()}",
                "imhere.see"
            )
        })

    val withTarget = CommandAPICommand("here")
        .withPermission("imhere.here")
        .withArguments(PlayerArgument("target").replaceSuggestions(ArgumentSuggestions.strings { plugin.server.onlinePlayers.map { it.name }.toTypedArray() }))
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val gray = ChatColor.GRAY
            val gold = ChatColor.GOLD
            plugin.server.broadcast(
                "$gray($gold${sender.name}$gray) (${sender.world.name}) ${sender.location.x.toInt()} ${sender.location.y.toInt()} ${sender.location.z.toInt()}",
                "imhere.see"
            )
        })
}