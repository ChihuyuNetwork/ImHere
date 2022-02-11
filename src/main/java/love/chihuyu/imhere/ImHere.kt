package love.chihuyu.imhere

import org.bukkit.plugin.java.JavaPlugin

class ImHere : JavaPlugin() {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        // Plugin startup logic
        logger.info("plugin has loaded.")
        getCommand("here")?.setExecutor(HereCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("plugin has unloaded.")
    }
}