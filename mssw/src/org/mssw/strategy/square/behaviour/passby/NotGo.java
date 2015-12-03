/**
 * 
 */
package org.mssw.strategy.square.behaviour.passby;

import org.mssw.strategy.square.behaviour.PassbyBehaviour;

/**
 * @author e047027
 *
 */
public class NotGo implements PassbyBehaviour {

	@Override
	public int passby() {
		return 0;
	}

	@Override
	public void setPassbyAmount(int passbyAmount) {
		System.out.println("Nothing to set");
	}

}
