package de.techgamez.pleezon.events.custom.TimeUpdate;

import de.techgamez.pleezon.events.EventType;
public class TimeUpdateType extends EventType {
    public final static TimeUpdateType Instance = new TimeUpdateType();
    private TimeUpdateType(){
        super();
    }
}
