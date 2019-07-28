package gin.kotlin.code.app.gin.kotlin.code.app.services

import com.google.inject.Singleton
import com.hubspot.slack.client.SlackClient
import com.hubspot.slack.client.SlackClientFactory
import com.hubspot.slack.client.SlackClientRuntimeConfig
import com.hubspot.slack.client.SlackWebClient
import io.dropwizard.lifecycle.Managed

fun getConfig() : SlackClientRuntimeConfig {
    return SlackClientRuntimeConfig.builder()
            .build()
}

@Singleton
class SlackService : Managed {

    var client : SlackClient? = null

    override fun start() {
        client =  SlackClientFactory.defaultFactory().build(getConfig())
        client?.
    }

    override fun stop() {
        client?.close()
    }
}