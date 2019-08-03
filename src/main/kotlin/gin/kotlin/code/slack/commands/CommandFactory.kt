package gin.kotlin.code.slack.commands

import com.github.seratch.jslack.api.model.Message
import com.google.inject.Inject
import gin.kotlin.code.slack.Client

data class CommandInfo(val type : String, val text : String = "")

class CommandFactory {

    @Inject
    lateinit var client : Client

    private val botName = "<@U1KH8H9FW>"
    private val regex = Regex("""$botName\s(\w*)\s*(\w*)""")

    private val commands = mapOf (
        "hello" to HelloCommand(),
        "fwd" to ForwardCommand(),
        "stop" to StopCommand(),
        "anonymous" to AnonymousCommand()
    )

    val topics = mapOf<String, Set<String>>()

    fun exec(message : Message) {
        val info = parse(message)
        commands[info.type]?.exec(client, this, info, message)
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