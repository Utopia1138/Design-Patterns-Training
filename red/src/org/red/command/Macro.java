package org.red.command;

import java.util.ArrayList;
import java.util.List;

public class Macro implements Command {
	private List<Command> commands = new ArrayList<>();

	public Macro( Command command ) {
		this.andThen( command );
	}

	public Macro andThen( Command command ) {
		this.commands.add( command );
		return this;
	}

	@Override
	public void execute() {
		commands.stream().forEachOrdered( c -> c.execute() );
	}
}
