package at.drblank.queststuff.Api;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ActionBarAPI {
    private final String nmsVersion;
    private Class ChatSerializerClass = null;
    private Constructor PacketPlayOutChatConstructor = null;
    private Method aMethod = null;
    private Method getHandleMethod = null;
    private Method sendPacketMethod = null;
    private Field playerConnectionField = null;
    private Object messageType = null;

    public ActionBarAPI(String nmsVersion) {
        this.nmsVersion = nmsVersion;
    }

    public Method getGetHandleMethod() {
        return this.getHandleMethod;
    }

    public Field getPlayerConnectionField() {
        return this.playerConnectionField;
    }

    public Method getSendPacketMethod() {
        return this.sendPacketMethod;
    }

    public void sendActionBar(Player player, String message) {
        if (this.ChatSerializerClass == null) try {
            this.ChatSerializerClass = Class.forName("net.minecraft.server." + this.nmsVersion + ".IChatBaseComponent$ChatSerializer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (this.PacketPlayOutChatConstructor == null) try {
            this.PacketPlayOutChatConstructor = Arrays.stream(Class.forName("net.minecraft.server." + this.nmsVersion + ".PacketPlayOutChat").getConstructors()).filter(constructor -> constructor.getParameterCount() == 3).findFirst().orElse(null);
            this.PacketPlayOutChatConstructor.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.aMethod == null) try {
            this.aMethod = Class.forName("net.minecraft.server." + this.nmsVersion + ".IChatBaseComponent$ChatSerializer").getDeclaredMethod("a", String.class);
            this.aMethod.setAccessible(true);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.getHandleMethod == null) try {
            this.getHandleMethod = player.getClass().getDeclaredMethod("getHandle");
            this.getHandleMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object entityPlayer = null;
        try {
            entityPlayer = this.getHandleMethod.invoke(player);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (entityPlayer == null) return;

        if (this.playerConnectionField == null) try {
            this.playerConnectionField = entityPlayer.getClass().getDeclaredField("playerConnection");
            this.playerConnectionField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Object playerConnection = null;
        try {
            playerConnection = this.playerConnectionField.get(entityPlayer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (this.sendPacketMethod == null) try {
            this.sendPacketMethod = playerConnection.getClass().getDeclaredMethod("sendPacket", Class.forName("net.minecraft.server." + this.nmsVersion + ".Packet"));
            this.sendPacketMethod.setAccessible(true);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String s = ChatColor.translateAlternateColorCodes('&', message);
        Object icbc = null;
        try {
            icbc = this.aMethod.invoke(this.ChatSerializerClass, "{\"text\": \"" + s +
                    "\"}");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Object packet = null;

        if (this.messageType == null) {
            Method aMethodMessageType = null;
            try {
                aMethodMessageType = Class.forName("net.minecraft.server." + this.nmsVersion + ".ChatMessageType").getDeclaredMethod("a", byte.class);
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                this.messageType = aMethodMessageType.invoke(Class.forName("net.minecraft.server." + this.nmsVersion + ".ChatMessageType"), (byte) 2);
            } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            packet = this.PacketPlayOutChatConstructor.newInstance(icbc, this.messageType, player.getUniqueId());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            this.sendPacketMethod.invoke(playerConnection, packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
