package me.RafaelAulerDeMeloAraujo.X1;


import java.util.UUID;

import org.bukkit.Material;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomManager {

	private UUID uniqueId;
	private boolean fullSopa, recraft;
	private Material armadura, espada;
	private int efeitos;
	
	public CustomManager(UUID uuid) {
		this.uniqueId = uuid;
		this.fullSopa = false;
		this.recraft = false;
		this.armadura = Material.GLASS;
		this.espada = Material.DIAMOND_SWORD;
		this.efeitos = 0;
	}
	public Material getArmadura() {
		return armadura;
	}
	public Material getEspada() {
		return espada;
	}
	public int getEfeitos() {
		return efeitos;
	}
	public void setArmadura(Material new2) {
		armadura = new2;
	}
	
	public void setRecraft(boolean new2) {
		recraft = new2;
	}
	public void setFullSopa(boolean new2) {
		fullSopa = new2;
	}
	public boolean isFullSopa() {
		return fullSopa;
	}
	public void setEfeitos(int new2) {
		efeitos = new2;
	}
	public boolean isRecraft() {
		return recraft;
	}
	public void setEspada(Material new2) {
		espada = new2;
	}
}
