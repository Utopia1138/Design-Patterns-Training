package org.axp.builder.builder;

import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;

public class TempTest {
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
		EmployeeFrame frame = new EmployeeFrame( ALICE, BOB, CAROL, DAN );		
		frame.setVisible( true );
	}
}
