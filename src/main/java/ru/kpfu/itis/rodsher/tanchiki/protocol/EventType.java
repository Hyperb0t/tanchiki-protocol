package ru.kpfu.itis.rodsher.tanchiki.protocol;

public enum EventType {
    GAME_RULES_SETTING(0),
    GAME_FIELD_SETTING(1),

    TANK_MOVED(2),
    TANK_FIRED(3),
    TANK_DAMAGED(4),

    WALL_DAMAGED(5),

    PLAYER_CONNECTED(6),
    PLAYER_LEFT(7),

    WAITING_FOR_PLAYERS(8),
    GAME_STARTED(9),
    GAME_FINISHED(10);

    public final Integer index;

    EventType(Integer index) {
        this.index = index;
    }
}
