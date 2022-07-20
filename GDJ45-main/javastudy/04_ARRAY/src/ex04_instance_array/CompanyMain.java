package ex04_instance_array;

public class CompanyMain {

	public static void main(String[] args) {
		
		// Employee를 최대 5명 가질 수 있는 Company 생성
		
		Company company = new Company(5);
		
		// 직원 추가
		company.addEmployee(new Employee("이사원", "기획부"));
		company.addEmployee(new Employee("김과장", "개발부"));
		company.addEmployee(new Employee("박대리", "영업부"));
		company.addEmployee(new Employee("최과장", "기술부"));
		company.addEmployee(new Employee("박대리", "개발부"));

		// 직원 조회
		company.inquiry("박대리");
		
		// 직원 해고
		company.fireEmployee("박대리", "기획부");
		
		// 모든 직원 정보 출력
		company.printAllEmployee();
		
	}

}
