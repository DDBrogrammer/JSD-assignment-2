package fa.training.problem02.utils.validate;

public class DepartmentValidate {
  public boolean ValidateName(String name) {
	  boolean checkOk=false;
	  if (name.length()>0&&name.length()<=50) {
		checkOk=true;
	}
	  return checkOk;
	  
  }
  public boolean validatDescription(String des) {
	  boolean checkOk=false;
	  if (des.length()>0&&des.length()<=100) {
		checkOk=true;
	}
	  return checkOk;
  }
  
  
}
