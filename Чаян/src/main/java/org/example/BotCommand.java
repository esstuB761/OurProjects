package org.example;

public enum BotCommand {
    ПОГОДА("/погода"),
    КОМАНДЫ("/команды"),
    СТАРТ("/start"),
    НИКНЕЙМ("/никнейм");

    private final String commandText;

    BotCommand(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }
}
