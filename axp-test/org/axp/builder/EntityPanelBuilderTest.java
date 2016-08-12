package org.axp.builder;

import org.axp.builder.builder.EntityFrame;
import org.axp.builder.builder.EntityPanel;
import org.axp.builder.builder.EntityPanelBuilder;
import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;

public class EntityPanelBuilderTest {
	static EntityPanel<Employee> panel;
	
	public static final Employee ALICE = new Employee()
		.setCommonName( "Alice" )
		.setFullName( "Alice Aqua" )
		.setDepartment( Department.TECHNOLOGY )
		.setPermanent( true );
	
	public static final Employee BOB = new Employee()
		.setCommonName( "Bob" )
		.setFullName( "Robert Blue" )
		.setDepartment( Department.SALES )
		.setPermanent( false );
	
	public static final Employee CAROL = new Employee()
		.setCommonName( "Carol" )
		.setFullName( "Carol Chartreuse" )
		.setDepartment( Department.MANAGEMENT )
		.setPermanent( true );
	
	public static final Employee DAN = new Employee()
		.setCommonName( "Dan" )
		.setFullName( "Daniel Dandelion" )
		.setDepartment( Department.HR )
		.setPermanent( true );
	
	public static void main( String...args ) {		
		panel = new EntityPanelBuilder<Employee>()
				.addColumn( "Common name", e -> panel.addTextBox( e.getCommonName(), 10, e::setCommonName ) )
				.addColumn( "Full name", e -> panel.addTextBox( e.getFullName(), 25, e::setFullName ) )
				.addColumn( "Department", e -> panel.addDropDown( e.getDepartment(), Department.values(), e::setDepartment ) )
				.addColumn( "Permanent?", e -> panel.addCheckBox( e.isPermanent(), e::setPermanent ) )
				.buildPanel();

		panel.addPrintButton( System.out::println );
		panel.addAddButton( Employee::new );
		
		for ( Employee e : new Employee[] { ALICE, BOB, CAROL, DAN } ) {
			panel.addEntity( e );
		}
		
		EntityFrame<Employee> frame = new EntityFrame<Employee>( panel, Employee::new );
		
		frame.setVisible( true );
	}
}
