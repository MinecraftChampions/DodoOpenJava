package io.github.minecraftchampions.dodoopenjava.message.card.enums;

public enum CountdownStyle {
    Day,
    Hour;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
