package org.red.iterator;

public interface ActorVisitor {
	default void visit( Actor actor ) { /* no-op */ }
	default void visit( Monster monster ) { /* no-op */ };
	default void visit( Hero monster ) { /* no-op */ };
}
