package wbhgef.model;

import org.eclipse.draw2d.geometry.Rectangle;

public class Employee extends Node {

	private String prenom;
	public static final String PROPERTY_FIRSTNAME = "EmployeePrenom";

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Employee emp = new Employee();
		emp.setName(this.getName());
		emp.setParent(this.getParent());
		emp.setPrenom(this.prenom);
		emp.setLayout(new Rectangle(getLayout().x + 10, getLayout().y + 10,
				getLayout().width, getLayout().height));
		return emp;
	}
}
