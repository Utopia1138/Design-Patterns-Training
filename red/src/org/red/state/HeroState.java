package org.red.state;

public interface HeroState {
	HeroState jump();
	HeroState attack();
	HeroState defend();
	HeroState tick();
}
