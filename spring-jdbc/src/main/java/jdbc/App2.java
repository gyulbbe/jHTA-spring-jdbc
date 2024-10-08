package jdbc;

import jdbc.vo.Employee;
import jdbc.vo.Employee.EmployeeBuilder;

public class App2 {

	public static void main(String[] args) {

		// EmployeeBuilder는 Employee 객체를 생성해주는 객체다.
		EmployeeBuilder builder = Employee.builder();
		Employee emp1 = builder.id(10)
			.firstName("홍")
			.lastName("길동")
			.email("hong@gmail.com")
			.build();
		
		Employee emp2 = Employee.builder()
								.id(100)
								.firstName("홍")
								.build();
		
	}

}
