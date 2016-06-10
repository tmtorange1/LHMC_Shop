package DunnoWhatToNameThis;

import java.util.Iterator;
import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Slist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// Command format: /slist
		if (cmd.getName().equalsIgnoreCase("slist")) { 
			sender.sendMessage(classyClass.chatTag + "All valid items: ");
			Set<String> items = classyClass.getInstance().getConfig().getKeys(false);
			Iterator<String> iter = items.iterator();
			while (iter.hasNext()) {
			    sender.sendMessage(classyClass.chatTag + "- " + iter.next());
			}
			
		}
		return false;
	}

}
