package at.drblank.queststuff.Api;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemAPI {
    ItemStack itemStack;

    ItemMeta itemMeta;

    SkullMeta skullMeta;

    List<String> lore = new ArrayList<>();

    private LeatherArmorMeta armorMeta;

    public ItemAPI(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemAPI(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemAPI setMaterial(Material material) {
        itemStack.setType(material);
        return this;
    }


    public ItemAPI setSubid(byte subid) {
        itemStack.getData().setData(subid);
        return this;
    }

    public ItemAPI setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemAPI setEnchantVisibility(boolean visible) {
        if (visible)
            if (this.itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))
                this.itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            else if (!this.itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS))
                this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemAPI setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemAPI setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemAPI setEnchantment(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> this.itemStack.addUnsafeEnchantment(enchantment, level));
        return this;
    }

    public ItemAPI setLore(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    public ItemAPI setLore(String... lore) {
        itemMeta.setLore(new ArrayList<>(Arrays.asList(lore)));
        return this;
    }

    public ItemAPI setSkullOwner(String owner) {
        itemStack.setItemMeta(itemMeta);
        skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(owner);
        return this;
    }

    public ItemAPI setBootsColor(Color color) {
        armorMeta.setColor(color);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(this.itemMeta);
        return itemStack;
    }

    public ItemStack buildSkull() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
