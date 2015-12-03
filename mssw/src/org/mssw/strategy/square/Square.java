/**
 * 
 */
package org.mssw.strategy.square;

import java.util.HashSet;

import org.mssw.strategy.player.Player;
import org.mssw.strategy.square.behaviour.BuyBehaviour;
import org.mssw.strategy.square.behaviour.PassbyBehaviour;
import org.mssw.strategy.square.behaviour.PickupBehaviour;

/**
 * @author michael.wambeek
 *
 */
public abstract class Square {
	protected String name;
	protected HashSet<Player> playersOnSquare;
	private BuyBehaviour buyBehaviour;
	private PickupBehaviour pickupBehaviour;
	private PassbyBehaviour passbyBehaviour;

	/**
	 * @param name
	 */
	public Square(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param player
	 */
	public void land(Player player) {
		this.playersOnSquare.add(player);
		System.out.println(player.getName() + " lands on " + this.name);
	}

	/**
	 * @param player
	 */
	public void moveForward(Player player) {
		this.playersOnSquare.remove(player);
		System.out.println(player.getName() + " leaves " + this.name);
	}

	public void moveBackward(Player player) {
		this.playersOnSquare.remove(player);
		System.out.println(player.getName() + " leaves " + this.name);
	}

	/**
	 * @param player
	 */
	public void moveOver(Player player) {
		System.out.println(player.getName() + " moves past " + this.name);
	}

}
