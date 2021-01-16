package de.techgamez.pleezon.packets.visitors;

import de.techgamez.pleezon.Constants;
import de.techgamez.pleezon.packets.ACClassVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class NetworkManagerVisitorUnobfu extends ACClassVisitor {

	public NetworkManagerVisitorUnobfu(ClassVisitor cv, boolean obf) {
		super(cv);
		
		System.out.println("NetworkManager is unobfuscated");
		Constants.obfuscated = false;
		obf = false;
		registerMethodVisitor("channelRead0", "(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V",
			mv -> new ChannelRead0Visitor(mv));
	}

	private static class ChannelRead0Visitor extends MethodVisitor {
		private boolean done;

		public ChannelRead0Visitor(MethodVisitor mv) {
			super(Opcodes.ASM4, mv);
		}

		@Override
		public void visitJumpInsn(int opcode, Label label) {
			super.visitJumpInsn(opcode, label);

			if (done || opcode != Opcodes.IFEQ)
				return;

			System.out.println("NetworkManagerVisitor.ChannelRead0Visitor.visitJumpInsn()");

			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "de/techgamez/pleezon/packets/events/ACEventFactory",
					"onReceivePacket", "(Lnet/minecraft/network/Packet;)Z", false);
			Label l1 = new Label();
			mv.visitJumpInsn(Opcodes.IFNE, l1);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitLabel(l1);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

			done = true;
		}
	}
}
