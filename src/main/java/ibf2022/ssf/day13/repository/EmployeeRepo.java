package ibf2022.ssf.day13.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ibf2022.ssf.day13.model.Employee;

@Repository
public class EmployeeRepo {

    final String dirPath = "/Users/suns88/data";
    final String filename = "employee.txt";
    
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if(employees == null) {
            employees = new ArrayList<Employee>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1980-10-15");
        Employee emp = new Employee("Derrick", "Tan", "derrick@gmail.com", "91234567", 7500, dt, "10 Ghim Moh Road", 272210);
        employees.add(emp);

        dt = df.parse("1972-02-18");
        emp = new Employee("Dennis", "Chew", "dennis@gmail.com", "98279183", 8500, dt, "28 Ghim Moh Road", 272228);
        employees.add(emp);

    }

    public List<Employee> findAll() {

        return employees;
    }


    public Boolean save(Employee employee) throws FileNotFoundException {
        Boolean result = employees.add(employee);

        File f = new File(dirPath + '/' + filename);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

        return result;
    }

    public Boolean delete(Employee employee) {
        //Employee e = employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get();
        
        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);
        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }

        return result;
    }

    public Employee findByEmailId(String email) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equalsIgnoreCase(email)).findFirst().get();
        return emp;
    }


    public Boolean updateEmployee(Employee em) {
        Employee emp = employees.stream().filter(e -> e.getEmail().equals(em.getEmail())).findFirst().get();

        int employeeIndex = employees.indexOf(emp);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
        }

        employees.add(em);

        return true;
    }

    //below code doesn't work correctly
    // public Boolean updateEmployee(Employee em) {
    //     Employee emp = employees.stream().filter(e -> e.getEmail().equals(em.getEmail())).findFirst().get();

    //     Integer idx = 0;
    //     if (emp != null) {
    //         idx = employees.indexOf(emp);
    //         employees.remove(emp);
    //         employees.add(emp);
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

}
