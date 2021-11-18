package fa.training.problem02.entity;

import java.sql.Date;
import java.util.Objects;

public class Employee {
	 private int id;
	 private Date birthDate;
	 private String firstName;
	 private String lastName;
	private Date hireDate;
	 private String gender;
	 public Employee () {};
	 @Override
	public String toString() {
		return "Employee [id=" + id + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", hireDate=" + hireDate + ", gender=" + gender + "]";
	}
	public Employee(int id, Date birthDate, String firstName, String lastName,  String gender,Date hireDate) {
		super();
		this.id = id;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.gender = gender;
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, firstName, gender, hireDate, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(firstName, other.firstName)
				&& gender == other.gender && Objects.equals(hireDate, other.hireDate) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
