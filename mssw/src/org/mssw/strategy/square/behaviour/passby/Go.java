/**
 * 
 */
package org.mssw.strategy.square.behaviour.passby;

import org.mssw.strategy.square.behaviour.PassbyBehaviour;

/**
 * @author e047027
 *
 */
public class Go implements PassbyBehaviour {
	private int passbyAmount;

	@Override
	public int passby() {

		return passbyAmount;
	}

	@Override
	public void setPassbyAmount(int passbyAmount) {
		this.passbyAmount = passbyAmount;

	}

}
