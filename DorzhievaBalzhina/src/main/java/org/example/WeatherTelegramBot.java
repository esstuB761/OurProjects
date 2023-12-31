package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherTelegramBot extends TelegramLongPollingBot {

    private static final String[] films = {
            "Убийство священного оленя",
            "Лобстер",
            "Фабельманы",
            // Добавьте другие фильмы
    };
    private static final String OPEN_WEATHER_MAP_API_KEY = "6862442813:AAFjEvE-LgzXcEtlRxF8Q_f7Bin3i-c1Pi0";
    private boolean startCommandProcessed = false;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String messageText = update.getMessage().getText().trim().toLowerCase();

                if (!startCommandProcessed) {
                    if (messageText.equals(BotCommand.СТАРТ.getCommandText())) {
                        sendResponse(update.getMessage().getChatId(), "Привет! Я кино-бот!Введите /comands ");
                        startCommandProcessed = true;
                    } else {
                        return;
                    }
                }
                String lowerText = messageText.toLowerCase();

                if (lowerText.startsWith(BotCommand.ПОГОДА.getCommandText())) {
                    String cityName = lowerText.replace(BotCommand.ПОГОДА.getCommandText(), "").trim();
                    if (!cityName.isEmpty()) {
                        String weatherInfo = getWeatherInfo(cityName);
                        sendResponse(update.getMessage().getChatId(), weatherInfo);
                    } else {
                        sendErrorMessage(update.getMessage().getChatId(), "Не указан город. Используйте /погода <Город>");
                    }
                }
                else if (lowerText.startsWith(BotCommand.ФИЛЬМ.getCommandText())) {
                    String film = lowerText.replace(BotCommand.ФИЛЬМ.getCommandText(), "").trim();
                    if (!film.isEmpty()) {
                        sendResponse(update.getMessage().getChatId(), "Посмотрите: " + film);
                    } else {
                        // Вызываем метод для получения случайного фильма
                        String randomFilm = getRandomFilm();
                        sendResponse(update.getMessage().getChatId(), "Посмотрите: " + randomFilm);
                    }
                }
                else if (lowerText.startsWith(BotCommand.ФИО.getCommandText())) {
                    String Fio = lowerText.replace(BotCommand.ФИО.getCommandText(), "").trim();
                    if (!Fio.isEmpty()) {
                        sendResponse(update.getMessage().getChatId(),"Здравствуйте, студет Б761-2 " + Fio);
                    } else {
                        sendErrorMessage(update.getMessage().getChatId(), "Не введено ФИО. Используйте /фио <Ваше ФИО>");
                    }
                }
                else if (lowerText.startsWith(BotCommand.ФИЛЬМ.getCommandText())) {
                    String Film = lowerText.replace(BotCommand.ФИЛЬМ.getCommandText(), "").trim();
                    if (!Film.isEmpty()) {
                        sendResponse(update.getMessage().getChatId(),"Посмотрите: " + Film);
                    }
                }
                else if (lowerText.equals(BotCommand.КОМАНДЫ.getCommandText())) {
                    sendResponse(update.getMessage().getChatId(), "Вот список доступных вам команд:\n" +
                            BotCommand.КОМАНДЫ.getCommandText() + " - вывести список всех доступных команд\n"+
                            BotCommand.ФИО.getCommandText()+" - ввести ваше ФИО\n"+
                            BotCommand.ФИЛЬМ.getCommandText()+" - вывести рандомный фильм\n");
                } else if (lowerText.equals("пока")) {
                    sendResponse(update.getMessage().getChatId(), "До встречи!");
                } else if(!lowerText.equals(BotCommand.СТАРТ.getCommandText())){
                    processNormalMessage(update.getMessage().getChatId(), lowerText);
                }
            }
        }
    }
    private void sendCitiesList() {
        //todo
    }
    private void processNormalMessage(long chatId, String messageText) {
        sendResponse(chatId, "Увы, но программист, который меня писал, слишком ленив, чтобы расписать более сложные алгоритмы обработки сообщений, поэтому я не могу разобрать это: " + messageText);
    }

    private void sendResponse(long chatId, String responseMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(responseMessage);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendErrorMessage(long chatId, String errorMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(errorMessage);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static String getWeatherInfo(String cityName) {
        try {
            URL apiUrl = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + OPEN_WEATHER_MAP_API_KEY);

            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());

                String weatherDescription = jsonNode.path("weather").get(0).path("description").asText();
                double temperature = jsonNode.path("main").path("temp").asDouble() - 273.15;
                double tempMin = jsonNode.path("main").path("temp_min").asDouble() - 273.15;
                double tempMax = jsonNode.path("main").path("temp_max").asDouble() - 273.15;
                int humidity = jsonNode.path("main").path("humidity").asInt();
                double windSpeed = jsonNode.path("wind").path("speed").asDouble();

                return String.format("Информация о погоде в %s:\n%s" +
                                "\nТемпература: %.2f°C (Мин: %.2f°C, Макс: %.2f°C)" +
                                "\nВлажность: %d%%" +
                                "\nСкорость ветра: %.2f м/с",
                        cityName, weatherDescription, temperature, tempMin, tempMax, humidity, windSpeed);
            } else {
                return "Ошибка при получении информации о погоде. Код ответа: " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Произошла ошибка при получении информации о погоде.";
        }
    }

    private String getRandomFilm() {
        if (films.length > 0) {
            int randomIndex = (int) (Math.random() * films.length);
            return films[randomIndex];
        } else {
            return "Список фильмов пуст.";
        }
    }

    @Override
    public String getBotUsername() {
        return "FilmGenerate_bot";
    }

    @Override
    public String getBotToken() {
        return "6862442813:AAFjEvE-LgzXcEtlRxF8Q_f7Bin3i-c1Pi0";
    }
}


