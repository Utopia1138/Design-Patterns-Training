package org.axp.builder.builder;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class EntityPanel<E> extends JPanel {
	private static final long serialVersionUID = -2937775783589472191L;
	
	private GridBagLayout gridbag;
	private GridBagConstraints c;
	private Font bold;
	
	public EntityPanel() {
		super();
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.insets = new Insets( 2, 3, 2, 3 );
		bold = getFont().deriveFont( Font.BOLD );
		setLayout( gridbag );
		
		String[] headings = headings();
		int last = headings.length - 1;
		
		for ( int i = 0; i < headings.length; i++ ) {
			if ( i == last ) {
				lastInRow();
			}
			
			addHeading( headings[i] );
		}
	}
	
	protected abstract String[] headings();
	
	protected void lastInRow() {
		c.gridwidth = GridBagConstraints.REMAINDER;
	}
	
	protected void addComponent( Component component ) {
		gridbag.setConstraints( component, c );
		add( component );
		c.gridwidth = 1;
	}
	
	protected void addHeading( String text ) {
		JLabel label = new JLabel( text );
		label.setFont( bold );
		label.setHorizontalAlignment( JLabel.CENTER );
		addComponent( label );
	}
	
	protected void addTextBox( String initialValue, int columns ) {
		JTextField textBox = new JTextField( initialValue );
		textBox.setColumns( columns );
		addComponent( textBox );
	}
	
	protected void addDropDown( Enum<?> value, Enum<?>[] allValues ) {
		JComboBox<Enum<?>> dropDown = new JComboBox<>( allValues );
		dropDown.setSelectedItem( value );
		addComponent( dropDown );
	}

	protected void addCheckBox( boolean checked ) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected( checked );
		addComponent( checkBox );
	}
	
	public abstract void addRow( E entity );
}
