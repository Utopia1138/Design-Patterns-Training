package org.axp.command;

/**
 * This models a command that we can execute on our system; e.g. "Restart Tomcat". It's a single-method
 * class, which allows us to use lambda expressions rather than writing concrete command classes.
 */
public interface Command {
    public void execute();
}