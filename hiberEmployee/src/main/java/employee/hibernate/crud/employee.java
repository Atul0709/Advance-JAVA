package employee.hibernate.crud;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Change the data type to Long

    private String name;
    private String department;
    private double salary;
    private String contact;
	    
	    
    public employee() {
		// TODO Auto-generated constructor stub
	}

		public employee(int id, String name, String department, double salary, String contact) {
	
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.contact = contact;
	}



		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDepartment() {
			return department;
		}


		public void setDepartment(String department) {
			this.department = department;
		}


		public double getSalary() {
			return salary;
		}


		public void setSalary(double salary) {
			this.salary = salary;
		}


		public String getContact() {
			return contact;
		}


		public void setContact(String contact) {
			this.contact = contact;
		}


		@Override
		public String toString() {
			return "employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary
					+ ", contact=" + contact + "]";
		}

		
	    // Getters and setters
	    // Constructor
	}

