package com.managementconsole.OID;


import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OIDUser {
    public OIDUser() {
    }
    
    private String employeeNumber;
    private String firstName;
    private String lastName;
    
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    
    public void setUserDetailsFromSession(String userName){
        String employeeNumber = null;
        try{            
            OIDTransaction  oidTransaction = new OIDTransaction();            
            TreeMap user = oidTransaction.GetCustomerServiceOidValues(userName);                                      
              
            if(user != null){            
                employeeNumber = (String)user.get("empNumber");
                System.out.println("employeeNumber ............." + employeeNumber);
                this.setEmployeeNumber(trimLeadingZeros(employeeNumber)); 
                
                firstName = (String)user.get("firstName");
                System.out.println("firstName ............." + firstName);
                this.setFirstName(firstName); 
                
                lastName = (String)user.get("lastName");
                System.out.println("lastName ............." + lastName);
                this.setLastName(lastName); 
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String trimLeadingZeros(String employeeNumber){
        String modEmpNumber = "";
        try {
            String regex = "^0*";         
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(employeeNumber);
            modEmpNumber=m.replaceAll("");
        }catch (Exception e) {
            System.out.println("Error while trimming employeeNumber ... " + e);
        }
        return modEmpNumber;
    }
    
    public static void main(String[] args) {
        OIDUser oIDUser = new OIDUser();
        oIDUser.setUserDetailsFromSession("test100@us.fujitsu.com");
        System.out.println(oIDUser.getEmployeeNumber());
    }
}
    