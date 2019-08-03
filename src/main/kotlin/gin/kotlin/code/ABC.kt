package gin.kotlin.code

fun main(args : Array<String>) {
    val botName = "<@U1KH8H9FW>"
    val regex = Regex("""$botName\s(\w*)\s*(\w*)""")

    parse(regex, "<@U1KH8H9FW>")
    parse(regex, "<@U1KH8H9FW> fwd")
    parse(regex, "<@U1KH8H9FW> fwd client")
    parse(regex, "<@U1KH8H9FW> fwd client abc")

}

private fun parse(regex: Regex, text : String) {
    val results = regex.find(text) ?: return
    val (command, topic) = results!!.destructured
    println("$command -> $topic")
}