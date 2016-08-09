package org.axp.builder.builder;

import org.axp.builder.model.Department;
import org.axp.builder.model.Employee;

public class EmployeePanel extends EntityPanel<Employee> {
	private static final long serialVersionUID = 1015914417282038714L;

	@Override
	protected String[] headings() {
		return new String[] { "Common name", "Full name", "Department", "Permanent?" }; 
	}

	@Override
	public void addRow( Employee e ) {
		addTextBox( e.getCommonName(), 10 );
		addTextBox( e.getFullName(), 25 );
		addDropDown( e.getDepartment(), Department.values() );
		lastInRow();
		addCheckBox( e.isPermanent() );
	}
}
