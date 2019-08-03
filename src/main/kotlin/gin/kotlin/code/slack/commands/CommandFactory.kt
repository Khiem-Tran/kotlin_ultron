package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

data class CommandInfo(val type : String, val text : String = "")

class CommandFactory : KoinComponent {

    private val botName = "<@ULMTY0Z9P>"
    private val regex = Regex("""$botName\s(\w*)\s*(\w*)""")

    private val helloCommand : Command by inject(named("hello"))
    private val forwardCommand : Command by inject(named("forward"))
    private val stopCommand : Command by inject(named("stop"))
    private val anonymousCommand : Command by inject(named("anonymous"))


    private val commands : Map<String, Command> = mapOf (
        "hello" to helloCommand,
        "fwd" to forwardCommand,
        "stop" to stopCommand,
        "anonymous" to anonymousCommand
    )

    val topics = mutableMapOf<String, Set<String>>()

    fun exec(message : Message) {
        val info = parse(message)
        commands[info.type]?.exec(info, message)
    }

    private fun parse(message: Message) : CommandInfo {
        if (message.type == "hello") return CommandInfo("hello")
        if (!message.text.contains(botName)) return CommandInfo("ignore")

        val results = regex.find(message.text)
        return when (results) {
            null -> CommandInfo("anonymous")
            else -> {
                val (type, text) = results!!.destructured
                CommandInfo(type, text)
            }
        }
    }
}