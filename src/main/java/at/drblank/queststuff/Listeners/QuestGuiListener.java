package at.drblank.queststuff.Listeners;

import at.drblank.queststuff.Api.ItemAPI;
import at.drblank.queststuff.QuestStuff;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class QuestGuiListener implements Listener, CommandExecutor {

    Inventory inventory = Bukkit.createInventory(null, 45, QuestStuff.getInstance().getConfig().getString("QuestNotifications.questgui"));

    public void openInventoryForPlayer(Player player) {
        player.playSound(player.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 3.0F, 1.0F);
        player.openInventory(this.inventory);
        ItemStack i5 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta m5 = i5.getItemMeta();
        m5.setDisplayName(" ");
        i5.setItemMeta(m5);
        for (int i = 0; i < this.inventory.getSize(); i++)
            this.inventory.setItem(i, i5);


    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(QuestStuff.getInstance().getConfig().getString("QuestNotifications.questgui")))
            return;
        Player player = (Player)event.getWhoClicked();
        event.setCancelled(true);
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {



            player.updateInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3.0F, 1.0F);
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(QuestStuff.getInstance().getConfig().getString("Errors.console"));
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            openInventoryForPlayer(player);
            player.sendMessage(QuestStuff.getInstance().getConfig().getString("QuestNotifications.prefix") + ""
                    + QuestStuff.getInstance().getConfig().getString("QuestNotifications.questguiopenmessage"));
        }

        return true;
    }
}
