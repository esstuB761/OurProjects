package org.example;

public enum BotCommand {
    ПОГОДА("/погода"),
    КОМАНДЫ("/comands"),
    ФИО("/fio"),
    СТАРТ("/start"),

    ФИЛЬМ("/film");



    private final String commandText;

    BotCommand(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }
}
