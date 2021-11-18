package fa.training.problem02.utils.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeValidate {
	 private final String VALIDATE_DATE="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
	 public boolean validateDate(String id) {
		 if (id.matches(VALIDATE_DATE)) { return true;}
		 else {return false;}
	 }
	 public  java.sql.Date getValidatedDateFromString(String date) {
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Date parsed = null;
			try {
				parsed = format.parse(date.replace("-", "").replace("/", "").replace(" ", ""));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		java.sql.Date sql = new java.sql.Date(parsed.getTime());
		return sql;
		
	 }
       public boolean validateFirstName(String name) {
    	   boolean checkOk=false;
    		  if (name.length()>0&&name.length()<=50) {
    			checkOk=true;
    		}
    		  return checkOk;
       }
       public boolean validateLastName(String name) {
    	   boolean checkOk=false;
    		  if (name.length()>0&&name.length()<=50) {
    			checkOk=true;
    		}
    		  return checkOk;
       }
       public boolean validateGender(String gender) {
    	   boolean checkOk=false;
   
    			if(gender.toUpperCase().equals("M")||gender.toUpperCase().equals("F")) {
    				checkOk=true;
    			
    		}
    		  return checkOk;
       }
       
	
	
	
	
	
	
	
	
	
}
