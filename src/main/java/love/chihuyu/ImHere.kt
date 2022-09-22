package love.chihuyu

import love.chihuyu.commands.CommandHere
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class ImHere : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)

        CommandHere.main.register()
        CommandHere.withTarget.register()
        // oh
    }
}