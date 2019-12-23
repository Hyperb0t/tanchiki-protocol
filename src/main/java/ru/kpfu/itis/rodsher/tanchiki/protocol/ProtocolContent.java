package ru.kpfu.itis.rodsher.tanchiki.protocol;

import ru.kpfu.itis.rodsher.tanchiki.models.Direction;

import java.util.Objects;

public class ProtocolContent {
    private Class entityType;
    private Float x;
    private Float y;
    private Float newX;
    private Float newY;
    private Direction direction;
    private String playerName;
    private Integer tankHealth;
    private Integer wallHealth;
    private Float tankSpeed;
    private Float shellSpeed;

    public ProtocolContent() {}

    public Class getEntityType() {
        return entityType;
    }

    public void setEntityType(Class entityType) {
        this.entityType = entityType;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getNewX() {
        return newX;
    }

    public void setNewX(Float newX) {
        this.newX = newX;
    }

    public Float getNewY() {
        return newY;
    }

    public void setNewY(Float newY) {
        this.newY = newY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getTankHealth() {
        return tankHealth;
    }

    public void setTankHealth(Integer tankHealth) {
        this.tankHealth = tankHealth;
    }

    public Integer getWallHealth() {
        return wallHealth;
    }

    public void setWallHealth(Integer wallHealth) {
        this.wallHealth = wallHealth;
    }

    public Float getTankSpeed() {
        return tankSpeed;
    }

    public void setTankSpeed(Float tankSpeed) {
        this.tankSpeed = tankSpeed;
    }

    public Float getShellSpeed() {
        return shellSpeed;
    }

    public void setShellSpeed(Float shellSpeed) {
        this.shellSpeed = shellSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtocolContent content = (ProtocolContent) o;
        return Objects.equals(entityType, content.entityType) &&
                Objects.equals(x, content.x) &&
                Objects.equals(y, content.y) &&
                Objects.equals(newX, content.newX) &&
                Objects.equals(newY, content.newY) &&
                direction == content.direction &&
                Objects.equals(playerName, content.playerName) &&
                Objects.equals(tankHealth, content.tankHealth) &&
                Objects.equals(wallHealth, content.wallHealth) &&
                Objects.equals(tankSpeed, content.tankSpeed) &&
                Objects.equals(shellSpeed, content.shellSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, x, y, newX, newY, direction, playerName, tankHealth, wallHealth, tankSpeed, shellSpeed);
    }
}
