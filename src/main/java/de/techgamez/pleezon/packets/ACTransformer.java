package de.techgamez.pleezon.packets;

import java.util.HashMap;

import de.techgamez.pleezon.packets.visitors.NetworkManagerVisitor;
import de.techgamez.pleezon.packets.visitors.NetworkManagerVisitorUnobfu;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;


import net.minecraft.launchwrapper.IClassTransformer;
public class ACTransformer implements IClassTransformer {

	private final HashMap<String, Class<? extends ACClassVisitor>> visitors = new HashMap<>();
	
	public ACTransformer() {
		visitors.put("net.minecraft.network.NetworkManager", NetworkManagerVisitorUnobfu.class);
		visitors.put("ek", NetworkManagerVisitor.class);
		
//		visitors.put("net.minecraft.client.gui.inventory.GuiContainer", GuiContainerVisitor.class);
//		visitors.put("ayl", GuiContainerVisitor.class);
	}
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		boolean obfuscated = true;
		if(!visitors.containsKey(transformedName))
			return basicClass;
		try {
			ClassReader reader = new ClassReader(basicClass);
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
			ClassVisitor visitor = visitors.get(transformedName)
					.getConstructor(ClassVisitor.class, boolean.class)
					.newInstance(writer, obfuscated);
			reader.accept(visitor, 0);
			return writer.toByteArray();
		} catch(Exception e) {
			e.printStackTrace();
			return basicClass;
		}
	}
}
