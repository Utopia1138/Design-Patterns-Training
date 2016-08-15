package org.axp.builder;

import java.time.LocalDate;
import java.time.Month;

import org.axp.builder.builder.EntityPanel;
import org.axp.builder.builder.EntityPanelBuilder;
import org.axp.builder.builder.ResizingFrame;
import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;
import org.axp.builder.model.Project;

public class EntityPanelBuilderTest {
	static EntityPanel<Employee> employeePanel;
	static EntityPanel<Project> projectPanel;
	
	public static LocalDate JUNE = LocalDate.of( 2016, Month.JUNE, 6 );
	public static LocalDate JULY = LocalDate.of( 2016, Month.JULY, 20 );
	public static LocalDate AUGUST = LocalDate.of( 2016, Month.AUGUST, 2 );
	
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
	
	public static final Employee[] EMPLOYEES = { ALICE, BOB, CAROL, DAN };
	
	public static final Project PROJECT_FEAR = new Project()
		.setProjectName( "Project Fear" )
		.setDevLead( ALICE )
		.setStart( JUNE );
	
	public static final Project PROJECT_DISTRUST = new Project()
		.setProjectName( "Project Mistrust" )
		.setParent( PROJECT_FEAR )
		.setDevLead( ALICE )
		.setStart( JULY )
		.setEnd( AUGUST )
		.setComplete( true );
	
	public static final Project[] PROJECTS = { PROJECT_FEAR, PROJECT_DISTRUST };
	
	public static void employeeFrame() {
		employeePanel = new EntityPanelBuilder<Employee>()
				.addColumn( "Common name", e -> employeePanel.addTextBox( e.getCommonName(), 10, e::setCommonName ) )
				.addColumn( "Full name", e -> employeePanel.addTextBox( e.getFullName(), 25, e::setFullName ) )
				.addColumn( "Department", e -> employeePanel.addDropDown( e.getDepartment(), Department.values(), e::setDepartment ) )
				.addColumn( "Permanent?", e -> employeePanel.addCheckBox( e.isPermanent(), e::setPermanent ) )
				.addColumn( "Delete", e -> employeePanel.addDeleteButton( e ) )
				.setPrintFunction( System.out::println )
				.setGenerateFunction( Employee::new )
				.buildPanel();
		
		for ( Employee e : EMPLOYEES ) {
			employeePanel.addEntity( e );
		}
		
		new ResizingFrame( employeePanel, "Employees" ).setVisible( true );
	}

	public static void projectFrame() {
		projectPanel = new EntityPanelBuilder<Project>()
				.addColumn( "Project name", p -> projectPanel.addTextBox( p.getProjectName(), 20, p::setProjectName ) )
				.addColumn( "Dev Lead", p -> projectPanel.addDropDown( p.getDevLead(), EMPLOYEES, p::setDevLead ) )
				.addColumn( "Parent project", p -> projectPanel.addDropDown( p.getParent(), PROJECTS, p::setParent ) )
				.addColumn( "Complete?", p -> projectPanel.addCheckBox( p.isComplete(), p::setComplete ) )
				.addColumn( "Delete", p -> projectPanel.addDeleteButton( p ) )
				.setPrintFunction( System.out::println )
				.setGenerateFunction( Project::new )
				.buildPanel();
		
		//private LocalDate start;
		//private LocalDate end;
		
		for ( Project p : PROJECTS ) {
			projectPanel.addEntity( p );
		}
		
		new ResizingFrame( projectPanel, "Projects" ).setVisible( true );
	}
	
	public static void main( String...args ) {		
		employeeFrame();
		projectFrame();
	}
}
