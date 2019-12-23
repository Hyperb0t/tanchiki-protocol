package ru.kpfu.itis.rodsher.tanchiki.protocol;

import ru.kpfu.itis.rodsher.tanchiki.models.Direction;
import ru.kpfu.itis.rodsher.tanchiki.models.Tank;
import ru.kpfu.itis.rodsher.tanchiki.models.Wall;

import java.util.ArrayList;
import java.util.List;

public class PackageParser {

    public Protocol parse(String pckg) {
        String[] parts = pckg.split("\\|");
        if(Integer.parseInt(parts[0]) == EventType.GAME_RULES_SETTING.index) {
            Protocol protocol = new Protocol(EventType.GAME_RULES_SETTING);
            ProtocolContent content = new ProtocolContent();
            content.setTankHealth(Integer.parseInt(parts[1]));
            content.setWallHealth(Integer.parseInt(parts[2]));
            content.setTankSpeed(Float.parseFloat(parts[3]));
            content.setShellSpeed(Float.parseFloat(parts[4]));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.GAME_FIELD_SETTING.index) {
            Protocol protocol = new Protocol(EventType.GAME_FIELD_SETTING);
            List<ProtocolContent> entities = new ArrayList<>();
            for(int i = 1; i < parts.length; i++) {
                ProtocolContent content = new ProtocolContent();
                switch(parts[i].charAt(0)) {
                    case 't':
                        String[] tankParts = parts[i].substring(2).split(",");
                        content.setX(Float.parseFloat(tankParts[0]));
                        content.setY(Float.parseFloat(tankParts[1]));
                        content.setDirection(Direction.getFromIndex(Integer.parseInt(tankParts[2])));
                        content.setPlayerName(tankParts[3]);
                        content.setEntityType(Tank.class);
                        break;
                    case 'w':
                        String[] wallParts = parts[i].substring(2).split(",");
                        content.setX(Float.parseFloat(wallParts[0]));
                        content.setY(Float.parseFloat(wallParts[1]));
                        content.setEntityType(Wall.class);
                        break;
                }
                entities.add(content);
            }
            protocol.setContent(entities);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.TANK_MOVED.index) {
            Protocol protocol = new Protocol(EventType.TANK_MOVED);
            ProtocolContent content = new ProtocolContent();
            content.setX(Float.parseFloat(parts[1]));
            content.setY(Float.parseFloat(parts[2]));
            content.setNewX(Float.parseFloat(parts[3]));
            content.setNewY(Float.parseFloat(parts[4]));
            content.setDirection(Direction.getFromIndex(Integer.parseInt(parts[5])));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.TANK_FIRED.index) {
            Protocol protocol = new Protocol(EventType.TANK_FIRED);
            ProtocolContent content = new ProtocolContent();
            content.setX(Float.parseFloat(parts[1]));
            content.setY(Float.parseFloat(parts[2]));
            content.setDirection(Direction.getFromIndex(Integer.parseInt(parts[3])));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.TANK_DAMAGED.index) {
            Protocol protocol = new Protocol(EventType.TANK_DAMAGED);
            ProtocolContent content = new ProtocolContent();
            content.setX(Float.parseFloat(parts[1]));
            content.setY(Float.parseFloat(parts[2]));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.WALL_DAMAGED.index) {
            Protocol protocol = new Protocol(EventType.WALL_DAMAGED);
            ProtocolContent content = new ProtocolContent();
            content.setX(Float.parseFloat(parts[1]));
            content.setY(Float.parseFloat(parts[2]));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.PLAYER_CONNECTED.index) {
            Protocol protocol = new Protocol(EventType.PLAYER_CONNECTED);
            ProtocolContent content = new ProtocolContent();
            content.setPlayerName(parts[1]);
            content.setX(Float.parseFloat(parts[2]));
            content.setY(Float.parseFloat(parts[3]));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.PLAYER_LEFT.index) {
            Protocol protocol = new Protocol(EventType.PLAYER_LEFT);
            ProtocolContent content = new ProtocolContent();
            content.setPlayerName(parts[1]);
            content.setX(Float.parseFloat(parts[2]));
            content.setY(Float.parseFloat(parts[3]));
            protocol.addToContent(content);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.WAITING_FOR_PLAYERS.index) {
            Protocol protocol = new Protocol(EventType.WAITING_FOR_PLAYERS);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.GAME_STARTED.index) {
            Protocol protocol = new Protocol(EventType.GAME_STARTED);
            return protocol;
        }
        if(Integer.parseInt(parts[0]) == EventType.GAME_FINISHED.index) {
            Protocol protocol = new Protocol(EventType.GAME_FINISHED);
            ProtocolContent content = new ProtocolContent();
            content.setPlayerName(parts[1]);
            return protocol;
        }
        return null;
    }
}
