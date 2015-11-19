package org.axp.strategy.random;

public interface RandomSource {
	public abstract String outcome();
	public abstract boolean hasMore();
}
