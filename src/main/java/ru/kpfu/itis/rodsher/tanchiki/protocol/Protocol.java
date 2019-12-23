package ru.kpfu.itis.rodsher.tanchiki.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Protocol {
    private EventType eventType;
    private List<ProtocolContent> content;

    public Protocol(EventType eventType) {
        this.eventType = eventType;
        content = new ArrayList<>();
    }

    public void addToContent(ProtocolContent object) {
        content.add(object);
    }

    public void setContent(List<ProtocolContent> content) {
        this.content = content;
    }

    public ProtocolContent getFromContent(Integer index) {
        return content.get(index);
    }

    public EventType getEventType() {
        return eventType;
    }

    public List<ProtocolContent> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protocol protocol = (Protocol) o;
        return eventType == protocol.eventType &&
                Objects.equals(content, protocol.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, content);
    }
}
