package org.example;

public enum BotCommand {
    ПОГОДА("/погода"),
    КОМАНДЫ("/команды"),
    ФИО("/имя"),
    СТАРТ("/start");
    //ГОРОДА("/города");

    private final String commandText;

    BotCommand(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }
}
