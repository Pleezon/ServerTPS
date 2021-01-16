package de.techgamez.pleezon.packets.events;
import de.techgamez.pleezon.events.custom.TimeUpdate.TimeUpdateType;
import de.techgamez.pleezon.events.custom.spawnPositionUpdate.SpawnPositionUpdateType;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.*;


public class ACEventFactory {

	public static boolean onReceivePacket(Packet<?> packet) {
		if(packet instanceof S03PacketTimeUpdate){
			TimeUpdateType.Instance.callbacks.forEach((c)->{
				c.apply(packet);
			});
		}else if(packet instanceof S05PacketSpawnPosition){
			SpawnPositionUpdateType.Instance.callbacks.forEach((c)->{
				c.apply(packet);
			});
		}
		return true;
	}
}