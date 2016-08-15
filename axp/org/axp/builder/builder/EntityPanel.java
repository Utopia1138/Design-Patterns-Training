package org.axp.builder.builder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

/**
 * This is a generic panel that can deal with CRUD operations (TODO: implement delete) on a list of some
 * entities.
 *
 * @param <E>
 */
public abstract class EntityPanel<E> extends JPanel {
	private static final long serialVersionUID = -2937775783589472191L;
	
	// UI Elements
	private JPanel mainPanel;
	private GridBagLayout gridbag;
	private GridBagConstraints c;
	private Font bold;
	
	// For date picker
	private static final String DATE_FORMAT = "dd-MMM-yyyy";
	
	// Data; TODO: could replace with an actual controller class
	private ArrayList<E> entities = new ArrayList<>();
	
	protected static class Range {
		public int start;
		public int len;
	}
	
	private HashMap<E,Range> entityPos = new HashMap<>();
	
	/**
	 * Construct the panel, showing a list of entities, and optionally print and add buttons
	 * 
	 * @param printFunction what the "print" button will execute; e.g. System.out::println. If null, no button will appear.
	 * @param generateFunction what the "add" button will fcall; e.g. MyEntity::new. If null, no button will appear.
	 */
	public EntityPanel( Consumer<String> printFunction, Supplier<E> generateFunction ) {
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
		
		if ( printFunction != null ) {
			JButton print = new JButton( "Print" );
			print.addActionListener( l -> printFunction.accept( entitiesToString() ) );
			add( print, BorderLayout.WEST );
		}
		
		if ( generateFunction != null ) {
			JButton add = new JButton( "Add" );
			add.addActionListener( l -> addEntity( generateFunction.get() ) );
			add( add, BorderLayout.EAST );
		}
	}
	
	/**
	 * A list of headings as appropriate for this entity. Should match the number of components in
	 * {@link #addRowForEntity(Object)}
	 * 
	 * @return a list of headings
	 */
	protected abstract String[] headings();
	
	protected void lastInRow() {
		c.gridwidth = GridBagConstraints.REMAINDER;
	}
	
	/**
	 * Generically add a component to the layout
	 * @param component some Swing component
	 */
	protected void addComponent( Component component ) {
		gridbag.setConstraints( component, c );
		mainPanel.add( component );
		c.gridwidth = 1;
	}
	
	/**
	 * Add a heading component
	 * @param text heading text
	 */
	protected void addHeading( String text ) {
		JLabel label = new JLabel( text );
		label.setFont( bold );
		label.setHorizontalAlignment( JLabel.CENTER );
		addComponent( label );
	}
	
	/**
	 * Add a text box to the layout
	 * @param initialValue initial contents of the text box
	 * @param columns width of the text box
	 * @param callback function to call when the contents change, e.g. myEntity::setMyField
	 */
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
	
	/**
	 * Add a drop-down to the layout
	 * @param value initially selected value
	 * @param allValues full list of values 
	 * @param callback function to call when the selected item changes, e.g. myEntity::setMyField
	 */
	@SuppressWarnings("unchecked")
	public <V> void addDropDown( V value, V[] allValues, Consumer<V> callback ) {
		JComboBox<V> dropDown = new JComboBox<>( allValues );
		dropDown.setSelectedItem( value );
		dropDown.addActionListener( l -> callback.accept( (V) dropDown.getSelectedItem() ) );
		addComponent( dropDown );
	}
	
	/**
	 * Add a drop-down to the layout, with a custom display function
	 * @param value initially selected value
	 * @param allValues full list of values
	 * @param displayFunction string to display for the item, e.g. myEntity::getEntityName
	 * @param callback function to call when the selected item changes, e.g. myEntity::setMyField
	 */
	@SuppressWarnings("unchecked")
	public <V> void addDropDown( V value, V[] allValues, Function<V,String> displayFunction, Consumer<V> callback ) {
		JComboBox<V> dropDown = new JComboBox<>( allValues );
		dropDown.setRenderer( new ListCellRenderer<V>() {
			JLabel label = new JLabel();
			
			@Override
			public Component getListCellRendererComponent(
					JList<? extends V> list, V val, int idx, boolean selected, boolean hasFocus ) {
				
				try {
					label.setText( displayFunction.apply( val ) );
				}
				catch ( NullPointerException e ) {
					label.setText( "{none}" );
				}
				
				return label;
			}} );
		
		dropDown.setSelectedItem( value );
		dropDown.addActionListener( l -> callback.accept( (V) dropDown.getSelectedItem() ) );
		addComponent( dropDown );
	}

	/**
	 * Add a checkbox to the layout
	 * @param checked whether it is initially checked
	 * @param callback function to call when the checkbox is toggled, e.g. myEntity::setMyField
	 */
	public void addCheckBox( boolean checked, Consumer<Boolean> callback ) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected( checked );
		checkBox.addChangeListener( l -> callback.accept( checkBox.isSelected() ) );
		addComponent( checkBox );
	}
	
	/**
	 * Add a date picker to the layout
	 * @param date initial date
	 * @param callback function to call when the date changes, e.g. myEntity::setMyDateField
	 */
	public void addDatePicker( LocalDate date, Consumer<LocalDate> callback ) {
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFormatForDatesCommonEra( DATE_FORMAT );
		DatePicker picker = new DatePicker( dateSettings );
		picker.setDate( date );
		picker.addDateChangeListener( l -> callback.accept( picker.getDate() ) );
		addComponent( picker );
	}
	
	/**
	 * Add a 'delete' button to the layout
	 * @param entity specific entity that this delete button is linked to
	 */
	public void addDeleteButton( E entity ) {
		JButton delete = new JButton( "X" );
		delete.addActionListener( l -> removeEntity( entity ) );
		addComponent( delete );
	}
	
	/**
	 * Load a new entity into the list and into the display
	 * @param entity some entity to add
	 */
	public void addEntity( E entity ) {
		entities.add( entity );
		
		Range componentSpan = new Range();
		componentSpan.start = mainPanel.getComponentCount();
		addRowForEntity( entity );
		componentSpan.len = mainPanel.getComponentCount() - componentSpan.start;
		entityPos.put( entity, componentSpan );
		
		revalidate();
	}
	
	/**
	 * Remove an entity from the list and the display
	 * @param entity some entity to remove
	 */
	public void removeEntity( E entity ) {
		if ( entities.remove( entity ) ) {
			Range componentSpan = entityPos.remove( entity );
			
			for ( int i = 0; i < componentSpan.len; i++ ) {
				mainPanel.remove( componentSpan.start );
			}
			
			revalidate();
		}
	}
	
	/**
	 * Convert the entities into a single string to pass to the print function
	 * @return the result of calling {@link Object#toString()} on each of the entities, separated by newlines 
	 */
	private String entitiesToString() {
		return entities.stream().map( E::toString ).collect( Collectors.joining( "\n" ) ) + "\n";
	}
	
	/**
	 * This is the meat of the function, called whenever a new row is added to the display. It should be implemented
	 * as a series of calls to {@link #addTextBox(String, int, Consumer)}, {@link #addCheckBox(boolean, Consumer)} etc,
	 * as appropriate to the entity, and the number of components in the row should match the length of
	 * {@link #headings()}
	 * @param entity a specific entity that this row corresponds to
	 */
	protected abstract void addRowForEntity( E entity );
}
