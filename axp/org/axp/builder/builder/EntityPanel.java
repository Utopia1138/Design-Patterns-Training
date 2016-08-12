package org.axp.builder.builder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class EntityPanel<E> extends JPanel {
	private static final long serialVersionUID = -2937775783589472191L;
	
	// UI Elements
	private JPanel mainPanel;
	private GridBagLayout gridbag;
	private GridBagConstraints c;
	private Font bold;
	
	// Data; TODO: could replace with an actual controller class
	private ArrayList<E> entities = new ArrayList<>();
	
	public EntityPanel() {
		super();
		mainPanel = new JPanel();
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.insets = new Insets( 2, 3, 2, 3 );
		bold = getFont().deriveFont( Font.BOLD );
		mainPanel.setLayout( gridbag );
		
		String[] headings = headings();
		int last = headings.length - 1;
		
		for ( int i = 0; i < headings.length; i++ ) {
			if ( i == last ) {
				lastInRow();
			}
			
			addHeading( headings[i] );
		}
		
		setLayout( new BorderLayout() );
		setBorder( new EmptyBorder( 5, 8, 8, 8 ) );
		add( mainPanel, BorderLayout.NORTH );
	}
	
	public void addPrintButton( Consumer<String> printFunction ) {
		JButton print = new JButton( "Print" );
		print.addActionListener( l -> printFunction.accept( entitiesToString() ) );
		add( print, BorderLayout.WEST );
	}
	
	public void addAddButton( Supplier<E> generateFunction ) {
		JButton add = new JButton( "Add" );
		add.addActionListener( l -> addEntity( generateFunction.get() ) );
		add( add, BorderLayout.EAST );
	}
	
	protected abstract String[] headings();
	
	protected void lastInRow() {
		c.gridwidth = GridBagConstraints.REMAINDER;
	}
	
	protected void addComponent( Component component ) {
		gridbag.setConstraints( component, c );
		mainPanel.add( component );
		c.gridwidth = 1;
	}
	
	protected void addHeading( String text ) {
		JLabel label = new JLabel( text );
		label.setFont( bold );
		label.setHorizontalAlignment( JLabel.CENTER );
		addComponent( label );
	}
	
	public void addTextBox( String initialValue, int columns, Consumer<String> callback ) {
		JTextField textBox = new JTextField( initialValue );
		textBox.setColumns( columns );
		textBox.addFocusListener( new FocusListener() {
			@Override public void focusGained( FocusEvent e ) { /* Nothing */ }

			@Override
			public void focusLost( FocusEvent e ) {
				callback.accept( textBox.getText() );
			}
		});
		
		addComponent( textBox );
	}
	
	@SuppressWarnings("unchecked")
	public <V> void addDropDown( V value, V[] allValues, Consumer<V> callback ) {
		JComboBox<V> dropDown = new JComboBox<>( allValues );
		dropDown.setSelectedItem( value );
		dropDown.addActionListener( l -> callback.accept( (V) dropDown.getSelectedItem() ) );
		addComponent( dropDown );
	}

	public void addCheckBox( boolean checked, Consumer<Boolean> callback ) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected( checked );
		checkBox.addChangeListener( l -> callback.accept( checkBox.isSelected() ) );
		addComponent( checkBox );
	}
	
	public void addEntity( E entity ) {
		entities.add( entity );
		addRowForEntity( entity );
		revalidate();
	}
	
	public String entitiesToString() {
		return entities.stream().map( E::toString ).collect( Collectors.joining( "\n" ) ) + "\n";
	}
	
	protected abstract void addRowForEntity( E entity );
}
