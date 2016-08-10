package org.axp.builder.builder;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;

public class EmployeeFrame extends JFrame {
	private static final long serialVersionUID = 6934127159559802729L;
	
	private EntityPanel<Employee> panel;
	private ArrayList<Employee> employees = new ArrayList<>();
	private JButton print;
	private JButton addEmp;

	public EmployeeFrame( Employee...currentEmployees ) {
		super( "Employees" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JPanel root = new JPanel();
		root.setBorder( new EmptyBorder( 5, 8, 8, 8 ) );
		root.setLayout( new BorderLayout( 5, 5 ) );

		panel = new EntityPanelBuilder<Employee>()
				.addColumn( "Common name", e -> panel.addTextBox( e.getCommonName(), 10, e::setCommonName ) )
				.addColumn( "Full name", e -> panel.addTextBox( e.getFullName(), 25, e::setFullName ) )
				.addColumn( "Department", e -> panel.addDropDown( e.getDepartment(), Department.values(), e::setDepartment ) )
				.addColumn( "Permanent?", e -> panel.addCheckBox( e.isPermanent(), e::setPermanent ) )
				.buildPanel();
		
		for ( Employee e : currentEmployees ) {
			addEmployee( e );
		}
		
		print = new JButton( "Print" );
		print.addActionListener( l -> {
			for ( Employee e : employees ) {
				System.out.println( e );
			}
		});

		addEmp = new JButton( "Add" );
		addEmp.addActionListener( l -> { 
			addEmployee( new Employee() );
			pack();
		});
		
		root.add( panel, BorderLayout.NORTH );
		root.add( print, BorderLayout.WEST );
		root.add( addEmp, BorderLayout.EAST );
		
		add( root );
		pack();
	}
	
	private void addEmployee( Employee e ) {
		employees.add( e );
		panel.addRow( e );
	}
}
