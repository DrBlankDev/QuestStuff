package at.drblank.queststuff.Commands;

import at.drblank.queststuff.QuestStuff;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(QuestStuff.getInstance().getConfig().getString("Errors.console"));
            return true;
        }
        if (!player.hasPermission(QuestStuff.getInstance().getConfig().getString("QuestPermissions.createquest"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    QuestStuff.getInstance().getConfig().getString("QuestNotifications.noperm")));
            return true;
        }

        if (args.length <= 0) {
            player.sendMessage(QuestStuff.getInstance().getConfig().getString("Errors.syntax") + label
                    + QuestStuff.getInstance().getConfig().getString("QuestNotifications.questcreatesyntax"));
            return true;
        }





        return true;
    }
}
