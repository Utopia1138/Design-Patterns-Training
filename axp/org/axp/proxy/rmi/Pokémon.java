package org.axp.proxy.rmi;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Set;
import java.util.TreeSet;

public class Pokémon implements Serializable {
	private static final long serialVersionUID = 4963516849574697754L;
	
	private String name;
	private String category;
	private Integer heightMm;
	private Integer weightG;
	private Set<String> attacks;
	
	private NumberFormat formatter = NumberFormat.getInstance();
	
	public Pokémon( String name ) {
		this.name = name;
		this.attacks = new TreeSet<>();
	}
	
	public Pokémon( String name, String category, int heightMm, int weightG ) {
		this( name );
		this.category = category;
		this.heightMm = heightMm;
		this.weightG = weightG;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		if ( name == null ) {
			throw new IllegalArgumentException( "Name cannot be null" );
		}
		
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory( String category ) {
		this.category = category;
	}

	public Integer getHeightMm() {
		return heightMm;
	}

	public void setHeightMm( Integer heightMm ) {
		this.heightMm = heightMm;
	}

	public Integer getWeightG() {
		return weightG;
	}

	public void setWeightG( Integer weightG ) {
		this.weightG = weightG;
	}
	
	public Set<String> attacks() {
		return attacks;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "== " ).append( name ).append( " ==" );
		
		if ( category != null ) {
			sb.append( "\nCategory: " ).append( category ).append( " type" );
		}
		
		if ( heightMm != null ) {
			sb.append( "\nHeight (mm): " ).append( formatter.format( heightMm ) );
		}
		
		if ( weightG != null ) {
			sb.append( "\nWeight (g): " ).append( formatter.format( weightG ) );
		}
		
		if ( !attacks.isEmpty() ) {
			sb.append( "\nAttacks: " ).append( String.join( ", ", attacks ) );
		}
		
		return sb.toString();
	}
	
	@Override
	public boolean equals( Object otherObj ) {
		return	( otherObj != null ) &&
				( otherObj instanceof Pokémon ) &&
				name.equals( ((Pokémon) otherObj).getName() ); 
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
