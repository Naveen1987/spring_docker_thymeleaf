package com.example.demo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockDemoApplication.class, args);
	}
	
	@GetMapping("/emps")
	public List<Employee> getEmps(){
		return Arrays.asList(
				new Employee(1, "Smith", 10000),
				new Employee(2, "Mike", 20000),
				new Employee(3, "Thomas", 30000),
				new Employee(4, "Kunal", 40000)
				);
	}
}


class Employee implements Serializable{
	
private int eid;
private String ename;
private int esal;

public Employee(int eid, String ename, int esal) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.esal = esal;
}

public Employee() {
	super();
}

public int getEid() {
	return eid;
}
public void setEid(int eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public int getEsal() {
	return esal;
}
public void setEsal(int esal) {
	this.esal = esal;
}

@Override
public String toString() {
	return "Employee [eid=" + eid + ", ename=" + ename + ", esal=" + esal + "]";
}

}