package ru.kpfu.itis.rodsher.tanchiki.protocol;

import ru.kpfu.itis.rodsher.tanchiki.models.*;

import java.util.List;

public class PackageSetter {
    public String setGameRules(Integer tankHealth, Integer wallHealth, Float tankSpeed, Float shellSpeed) {
        return EventType.GAME_RULES_SETTING.index + "|" + tankHealth + "|" + wallHealth + "|" + tankSpeed + "|" + shellSpeed;
    }

    public String setGameField(Field field) {
        String pckg = EventType.GAME_FIELD_SETTING.index + "";
        List<AbstractEntity> entities = field.getEntities();
        for(AbstractEntity entity : entities) {
            if(entity instanceof Tank) {
                pckg += "|t:" + entity.getX() + "," + entity.getY() + "," + ((Tank) entity).getDirection().index
                        + "," + ((Tank) entity).getOwner().getName();
            }
            if(entity instanceof Wall) {
                pckg += "|w:" + entity.getX() + "," + entity.getY();
            }
        }
        return pckg;
    }

    public String setTankMoved(Float previousX, Float previousY, Float newX, Float newY, Direction direction) {
        return EventType.TANK_MOVED.index + "|" + previousX + "|" + previousY + "|" + newX + "|" + newY + "|" + direction.index;
    }

    public String setTankFired(Float x, Float y, Direction direction) {
        return EventType.TANK_FIRED.index + "|" + x + "|" + y + "|" + direction.index;
    }

    public String setTankDamaged(Float x, Float y) {
        return EventType.TANK_DAMAGED.index + "|" + x + "|" + y;
    }

    public String setWallDamaged(Float x, Float y) {
        return EventType.WALL_DAMAGED.index + "|" + x + "|" + y;
    }

    public String setPlayerConnected(String playerName, Float tankX, Float tankY) {
        return EventType.PLAYER_CONNECTED.index + "|" + playerName + "|" + tankX + "|" + tankY;
    }

    public String setPlayerLeft(String playerName, Float tankX, Float tankY) {
        return EventType.PLAYER_LEFT.index + "|" + playerName + "|" + tankX + "|" + tankY;
    }

    public String setWaitingForPlayers() {
        return EventType.WAITING_FOR_PLAYERS.index + "";
    }

    public String setGameStarted() {
        return EventType.GAME_STARTED.index + "";
    }

    public String setGameFinished(String winnerPlayerName) {
        return EventType.GAME_FINISHED.index + "|" + winnerPlayerName;
    }

    public String setFromProtocol(Protocol protocol) {
        switch (protocol.getEventType()) {
            case GAME_RULES_SETTING:
                return setGameRules(protocol.getFromContent(0).getTankHealth(),
                        protocol.getFromContent(0).getWallHealth(),
                        protocol.getFromContent(0).getTankSpeed(),
                        protocol.getFromContent(0).getShellSpeed());
            case GAME_FIELD_SETTING:
                String pckg = EventType.GAME_FIELD_SETTING.index + "";
                List<ProtocolContent> entities = protocol.getContent();
                for(ProtocolContent entity : entities) {
                    if(entity.getEntityType().equals(Tank.class)) {
                        pckg += "|t:" + entity.getX() + "," + entity.getY() + "," + entity.getDirection().index
                                + "," + entity.getPlayerName();
                    }
                    if(entity.getEntityType().equals(Wall.class)) {
                        pckg += "|w:" + entity.getX() + "," + entity.getY();
                    }
                }
                return pckg;
            case TANK_MOVED:
                return setTankMoved(protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY(),
                        protocol.getFromContent(0).getNewX(), protocol.getFromContent(0).getNewY(),
                        protocol.getFromContent(0).getDirection());
            case TANK_FIRED:
                return setTankFired(protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY(),
                        protocol.getFromContent(0).getDirection());
            case TANK_DAMAGED:
                return setTankDamaged(protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY());
            case WALL_DAMAGED:
                return setWallDamaged(protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY());
            case PLAYER_CONNECTED:
                return setPlayerConnected(protocol.getFromContent(0).getPlayerName(),
                        protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY());
            case PLAYER_LEFT:
                return setPlayerLeft(protocol.getFromContent(0).getPlayerName(),
                        protocol.getFromContent(0).getX(), protocol.getFromContent(0).getY());
            case WAITING_FOR_PLAYERS:
                return setWaitingForPlayers();
            case GAME_STARTED:
                return setGameStarted();
            case GAME_FINISHED:
                return setGameFinished(protocol.getFromContent(0).getPlayerName());
            default:
                return null;
        }
    }
}
