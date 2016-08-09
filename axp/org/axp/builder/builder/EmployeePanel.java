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

import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;

public class EmployeePanel extends JPanel {
	private static final long serialVersionUID = 1015914417282038714L;

	private GridBagLayout gridbag;
	private GridBagConstraints c;
	private Font bold;
	
	public EmployeePanel() {
		super();
		gridbag = new GridBagLayout();
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.insets = new Insets( 2, 3, 2, 3 );
		bold = getFont().deriveFont( Font.BOLD );
		setLayout( gridbag );
		addHeading( "Common name" );
		addHeading( "Full name" );
		addHeading( "Department" );
		lastInRow();
		addHeading( "Permanent?" );
	}
	
	private void lastInRow() {
		c.gridwidth = GridBagConstraints.REMAINDER;
	}
	
	private void addComponent( Component component ) {
		gridbag.setConstraints( component, c );
		add( component );
		c.gridwidth = 1;
	}
	
	private void addHeading( String text ) {
		JLabel label = new JLabel( text );
		label.setFont( bold );
		label.setHorizontalAlignment( JLabel.CENTER );
		addComponent( label );
	}
	
	private void addTextBox( String initialValue, int columns ) {
		JTextField textBox = new JTextField( initialValue );
		textBox.setColumns( columns );
		addComponent( textBox );
	}
	
	private void addDropDown( Department department ) {
		JComboBox<Department> dropDown = new JComboBox<Department>( Department.values() );
		dropDown.setSelectedItem( department );
		addComponent( dropDown );
	}

	private void addCheckBox( boolean checked ) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setSelected( checked );
		addComponent( checkBox );
	}
	
	public void addEmployee( Employee e ) {
		addTextBox( e.getCommonName(), 10 );
		addTextBox( e.getFullName(), 25 );
		addDropDown( e.getDepartment() );
		lastInRow();
		addCheckBox( e.isPermanent() );
	}
}
