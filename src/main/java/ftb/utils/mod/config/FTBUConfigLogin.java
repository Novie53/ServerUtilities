package ftb.utils.mod.config;

import ftb.lib.FTBLib;
import ftb.lib.api.item.ItemStackSerializer;
import latmod.lib.Info;
import latmod.lib.config.ConfigEntryStringArray;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.*;

public class FTBUConfigLogin
{
	@Info("Message of the day. This will be displayed when player joins the server")
	private static final ConfigEntryStringArray motd = new ConfigEntryStringArray("motd", "Welcome to the server!");
	
	@Info({"Items to give player when he first joins the server", "Format: StringID Size Metadata", "Now supports NBT, still not recommended"})
	private static final ConfigEntryStringArray starting_items = new ConfigEntryStringArray("starting_items", "minecraft:apple 16 0");
	
	public static List<ItemStack> getStartingItems(UUID id)
	{
		ArrayList<ItemStack> list = new ArrayList<>();
		
		for(String s : starting_items.get())
		{
			ItemStack is = ItemStackSerializer.parseItem(s);
			if(is != null) list.add(is);
		}
		
		return list;
	}
	
	public static boolean printMotd(EntityPlayerMP ep)
	{
		for(String s : FTBUConfigLogin.motd.get())
			FTBLib.printChat(ep, s.replace("$player$", ep.getCommandSenderName()));
		return true;
	}
}