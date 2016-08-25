package org.axp.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.axp.visitor.OrganismTreeVisitor;

/**
 * This is the Composite part of the composite pattern.
 * 
 * Methods are provided to add new subcategories, and to recurse through them where necessary.
 */
public class Category extends OrganismTree {
	private HashSet<OrganismTree> subcategories = new HashSet<>();
	
	public Category( String name ) {
		super( name );
	}
	
	@Override
	public Category add( OrganismTree...subcategories ) {
		this.subcategories.addAll( Arrays.asList( subcategories ) );
		return this;
	}
	
	@Override
	public Collection<OrganismTree> removeIf( Predicate<? super OrganismTree> condition ) {
		ArrayList<OrganismTree> removed = new ArrayList<>();
		
		for ( OrganismTree subcat : subcategories ) {
			if ( condition.test( subcat ) ) {
				subcategories.remove( subcat );
				removed.add( subcat );
			}
			else {
				removed.addAll( subcat.removeIf( condition ) );
			}
		}
		
		return removed;
	}

	@Override
	public OrganismTree find( String name ) {
		if ( super.find( name ) != null ) {
			return this;
		}
		
		for ( OrganismTree subcat : subcategories ) {
			OrganismTree found = subcat.find( name );
			
			if ( found != null ) {
				return found;
			}
		}
		
		return null;
	}
	
	public Stream<OrganismTree> stream() {
		Stream<OrganismTree> s = super.stream();
		
		for ( OrganismTree subcat : this.subcategories ) {
			s = Stream.concat( s, subcat.stream() );
		}
		
		return s;
	}

	@Override
	public <E> void visit( OrganismTreeVisitor<E> visitor, E state ) {
		visitor.accept( this, state );
	}
}
