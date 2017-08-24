package com.example.demo;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@SpringBootApplication
//@RestController
@Controller
public class DemoSpringThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringThymeleafApplication.class, args);
	}
	@Bean
	CommandLineRunner cmdrun(EmpDao da){
		return records-> Stream.of("Mike|1000","Smith|2000","Thomas|3000").forEach(
				record-> {
					String ename=record.split(Pattern.quote("|"))[0];
					int esal=Integer.valueOf(record.split(Pattern.quote("|"))[1]);
					da.save(new Employee(ename, esal));
				}
				);
	}
	
	
	
	@Autowired
	EmpDao da;
	/*@GetMapping("/")
	public List<Employee> getIndex(){
	return da.getAll();	
	}
	*/
	
	@GetMapping("/")
	public ModelAndView getIndex(ModelAndView mp){
		mp.addObject("msg", da.getAll());
		mp.setViewName("index");
	return mp;
	}
	@GetMapping("/insert")
	public String getInsert(){
	return "newRecord";
	}
	
	@PostMapping("/insert")
	public String getSubmit(ModelMap mp,@RequestParam("ename") String ename,@RequestParam("esal") int esal){
//    System.out.println(ename+""+esal);
	da.save(new Employee(ename, esal));
	mp.addAttribute("msg", "Data Inserted Successfully");
	return "newRecord";
	}
}

@Repository
interface EmpDao extends CrudRepository<Employee, Integer>,EmpDaoCustom{
	
}

interface EmpDaoCustom{
	public List<Employee> getAll();
}

@Repository
class EmpDaoImpl implements EmpDaoCustom{
	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Employee> getAll() {
		Query q=em.createQuery("from Employee");
		List<Employee> l=q.getResultList();
		return l;
	}
	
}

@Entity
class Employee
{
	@Id
	@GeneratedValue
	private int eid;
	private String ename;
	private int esal;
	
	public Employee() {
		super();
	}
	
	public Employee(String ename, int esal) {
		super();
		this.ename = ename;
		this.esal = esal;
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
