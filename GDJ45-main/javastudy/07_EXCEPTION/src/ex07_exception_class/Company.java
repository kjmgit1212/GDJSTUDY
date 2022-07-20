package ex07_exception_class;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Company {

	private String name;  // 회사명
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	// 생성자, 추가, 삭제, 조회, 전체조회, 메뉴, 관리
	public Company(String name, int count) {
		this.name = name;
		employees = new Employee[count];
		sc = new Scanner(System.in);
	}
	
	// 추가
	// 배열의 Full Check 필요, 에러코드 1001
	public void addEmployee() throws EmployeeException, InputMismatchException {
		if(idx == employees.length)
			throw new EmployeeException("사원 추가 불가", 1001);
		System.out.print("고용 형태(1.정규 2.비정규) >>> ");
		int kind = sc.nextInt();
		System.out.print("사원번호 >>> ");
		long empNo = sc.nextLong();
		System.out.print("사원명 >>> ");
		String name = sc.next();
		switch(kind) {
		case 1:
			System.out.print("기본급 >>> ");
			int salary = sc.nextInt();
			employees[idx++] = new Regular(empNo, name, salary);
			break;
		case 2:
			System.out.print("시급 >>> ");
			int payPerHour = sc.nextInt();
			employees[idx++] = new Temporary(empNo, name, payPerHour);
			break;
		default:
			System.out.println("잘못된 선택. 사원 등록 취소"); return;
		}
		System.out.println("사원 등록 완료");
	}
	
	// 삭제
	// 배열의 Empty Check 필요, 에러코드 2001
	public void removeEmployee() throws EmployeeException {
		if(idx == 0)
			throw new EmployeeException("사원 삭제 불가", 2001);
		System.out.print("삭제할 사원번호 >>> ");
		long empNo = sc.nextLong();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.out.println("사원번호 " + empNo + " 사원을 삭제합니다.");
				System.arraycopy(employees, i + 1, employees, i, idx - 1 - i);
				employees[--idx] = null;
				return;
			}
		}
		System.out.println("사원번호 " + empNo + " 사원이 없습니다.");
	}
	
	// 조회
	// 배열의 Empty Check 필요, 에러코드 3001
	public void findEmployee() throws EmployeeException {
		if(idx == 0)
			throw new EmployeeException("사원 조회 불가", 3001);
		System.out.print("조회할 사원 번호 >>> ");
		long empNo = sc.nextLong();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.out.println("조회된 사원 " + employees[i]);
				return;
			}
		}
		System.out.println("사원번호 " + empNo + " 사원이 없습니다.");
	}
	
	// 전체조회
	// 배열의 Empty Check 필요, 에러코드 3001
	public void findAllEmployees() throws EmployeeException {
		if(idx == 0)
			throw new EmployeeException("사원 조회 불가", 3001);
		System.out.println("===전체 사원 목록===");
		for(int i = 0; i < idx; i++)
			System.out.println((i + 1) + "번째 사원 " + employees[i]);
	}
	
	// 메뉴
	public void menu() {
		System.out.println();
		System.out.println("[" + name + "] 관리프로그램");
		System.out.println("   1.사원추가");
		System.out.println("   2.사원삭제");
		System.out.println("   3.사원조회");
		System.out.println("   4.전체조회");
		System.out.println("   0.관리종료");
		System.out.println("-------------");
	}
	
	// 관리
	public void manage() {
		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addEmployee(); break;
				case 2: removeEmployee(); break;
				case 3: findEmployee(); break;
				case 4: findAllEmployees(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default: System.out.println("잘못된 선택입니다.");
				}
			} catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 다시 시도하세요.");
				sc.next();
			} catch(EmployeeException e) {
				System.out.println(e.getMessage() + "[에러코드 : " + e.getErrorCode() + "]");
			}
		}
	}
	
}
