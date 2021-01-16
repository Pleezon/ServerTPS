package de.techgamez.pleezon.events;

import java.util.ArrayList;
import java.util.function.Function;

public abstract class EventType {
    public final ArrayList<Function> callbacks = new ArrayList<>();
    public EventType() {

    }
}
