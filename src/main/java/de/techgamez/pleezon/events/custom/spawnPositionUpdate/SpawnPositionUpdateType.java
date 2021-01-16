package de.techgamez.pleezon.events.custom.spawnPositionUpdate;

import de.techgamez.pleezon.events.EventType;

public class SpawnPositionUpdateType extends EventType {
    public final static SpawnPositionUpdateType Instance = new SpawnPositionUpdateType();
    private SpawnPositionUpdateType(){
        super();
    }
}
