package gin.code;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.rtm.RTMClient;
import com.github.seratch.jslack.api.rtm.message.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {

  private static final String TOKEN = "xoxb-708285948944-701950033329-KlcM7LMBNA01VrA9XyADZKyN";

  public static void main(String[] args) {

    JsonParser parser = new JsonParser();
    try (RTMClient rtm = new Slack().rtm(TOKEN)) {

      rtm.addMessageHandler((sms) -> {
        JsonObject json = parser.parse(sms).getAsJsonObject();
        if (json.get("type").getAsString() != null && json.get("type").equals("hello")) {
          rtm.sendMessage(Message.builder()
              .id(System.currentTimeMillis())
              .channel("#general")
              .text("Hi!")
              .build().toJSONString());
        }
        System.out.println(json.toString());
      });


      rtm.connect();

      while (true);

    } catch(Exception ex) {

    }
  }
}
