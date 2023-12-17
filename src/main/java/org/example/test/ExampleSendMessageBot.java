package org.example.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExampleSendMessageBot {
    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Текст сообщения");
        HttpURLConnection httpConnection = (HttpURLConnection) new URL("https://api.telegram.org/bot6937427692:AAHlAhhoKk0yCgGWALnfdVuSMPXqr8H2_h4/sendMessage?chat_id=-4057397876&text=" + stringBuilder
        ).openConnection();
        httpConnection.getInputStream();
        httpConnection.disconnect();
    }
}
