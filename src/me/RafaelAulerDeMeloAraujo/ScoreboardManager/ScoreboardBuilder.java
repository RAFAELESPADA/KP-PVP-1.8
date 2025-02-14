package me.RafaelAulerDeMeloAraujo.ScoreboardManager;

import net.helix.core.bukkit.account.HelixPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.RafaelAulerDeMeloAraujo.Coins.Coins;
import me.RafaelAulerDeMeloAraujo.SpecialAbility.Habilidade;
import me.RafaelAulerDeMeloAraujo.main.Main;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.format.HelixDecimalFormat;


public class ScoreboardBuilder {

	public ScoreboardBuilder(Main plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
private static String text = "";
private static WaveAnimation waveAnimation;

	public void init(Player onlines) {
	
onlines.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
		
		objective.setDisplayName(Main.messages.getString("ScoreBoardTitle").replace("&", "§"));
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
						
						String l10 = "§3";
						String l9 = Main.messages.getString("ScoreBoardGroup").replace("&", "§");
						String l8 = "§2";
						String l7 = Main.messages.getString("ScoreBoardKills").replace("&", "§");
						String l6 = Main.messages.getString("ScoreBoardDeaths").replace("&", "§");
						String l5 = Main.messages.getString("ScoreBoardKS").replace("&", "§");
						String l4 = "§1";
						String l3 = Main.messages.getString("ScoreBoardCoins").replace("&", "§");
						String l2 = Main.messages.getString("ScoreBoardLevel").replace("&", "§");
						String l22 = Main.messages.getString("ScoreBoardNeedXP").replace("&", "§");
						String lt = Main.messages.getString("ScoreBoardPlayers").replace("&", "§");
						String lk = Main.messages.getString("ScoreBoardKit").replace("&", "§");
						String l1 = "§0";
						
						scoreboard.registerNewTeam("groupPrefix").addEntry(l9);
						scoreboard.registerNewTeam("kills").addEntry(l7);
						scoreboard.registerNewTeam("deaths").addEntry(l6);
						scoreboard.registerNewTeam("killstreak").addEntry(l5);
						scoreboard.registerNewTeam("coins").addEntry(l3);
						scoreboard.registerNewTeam("kit").addEntry(lk);
						scoreboard.registerNewTeam("players").addEntry(lt);
						scoreboard.registerNewTeam("rank").addEntry(l2);
						scoreboard.registerNewTeam("nextrank").addEntry(l22);
						objective.getScore(l10).setScore(12);
						objective.getScore(l9).setScore(11);
						objective.getScore(l8).setScore(10);
						objective.getScore(l7).setScore(9);
						objective.getScore(l6).setScore(8);
						objective.getScore(l5).setScore(7);
						objective.getScore(l4).setScore(6);
						objective.getScore(l3).setScore(5);
						objective.getScore(l2).setScore(4);
						objective.getScore(l22).setScore(3);
						objective.getScore(lt).setScore(2);
						objective.getScore(lk).setScore(1);
						objective.getScore(l1).setScore(0);
						onlines.setScoreboard(scoreboard);
						update(onlines);
				}

	
	
	public void update(Player player) {
		if (player.getScoreboard().getObjective("pvp") == null) {
			return;
		}
		HelixPlayer pvp = HelixBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		Scoreboard scoreboard = player.getScoreboard();
		String group = Coins.perms.getPrimaryGroup(player) != null ? Coins.perms.getPrimaryGroup(player) : "No Group";
		scoreboard.getTeam("groupPrefix").setSuffix(group);
		scoreboard.getTeam("kills").setSuffix(HelixDecimalFormat.format(pvp.getPvp().getKills()));
		scoreboard.getTeam("deaths").setSuffix(HelixDecimalFormat.format(pvp.getPvp().getDeaths()));
		scoreboard.getTeam("killstreak").setSuffix(HelixDecimalFormat.format(pvp.getPvp().getKillstreak()));
		scoreboard.getTeam("coins").setSuffix(HelixDecimalFormat.format(pvp.getPvp().getCoins()));
		scoreboard.getTeam("players").setSuffix(HelixDecimalFormat.format(Bukkit.getOnlinePlayers().size()));
		scoreboard.getTeam("kit").setSuffix((String.valueOf(Habilidade.getAbility(player))));
		scoreboard.getTeam("rank").setSuffix((String.valueOf(Level.getLevel(player))));
		scoreboard.getTeam("nextrank").setSuffix((String.valueOf(Level.getXPToLevelUp(player))));
	}
}