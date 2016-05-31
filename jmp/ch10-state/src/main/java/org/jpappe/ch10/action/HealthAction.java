package org.jpappe.ch10.action;

import org.jpappe.ch10.model.CharacterEntity;

/**
 * represents actions performed on a character that affect
 * their health state
 * 
 * @author Jacob Pappe
 *
 */
public interface HealthAction {

	void performAction(CharacterEntity c);
}
