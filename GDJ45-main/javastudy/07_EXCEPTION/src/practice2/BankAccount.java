package practice2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccount {

	private String owner;  // 예금주
	private String accNo;  // 계좌번호
	private long balance;  // 잔고
	private Scanner sc;
	private BankAccount[] accounts;  // 자주쓰는계좌
	private int idx;
	
	public BankAccount(String owner, String accNo, long balance) {
		super();
		this.owner = owner;
		this.accNo = accNo;
		this.balance = balance;
		sc = new Scanner(System.in);
		accounts = new BankAccount[3];
	}
	
	// 자주쓰는계좌등록
	public void addAccount() {
		try {
			System.out.println("***자주쓰는계좌등록(" + (accounts.length - idx) + "개등록가능)***");
			System.out.print("예금주 >>> ");
			String owner = sc.next();
			System.out.print("계좌번호 >>> ");
			String accNo = sc.next();
			System.out.print("잔고 >>> ");
			long balance = sc.nextLong();
			accounts[idx++] = new BankAccount(owner, accNo, balance);
			System.out.println("계좌등록성공!");
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("더 이상 계좌를 등록할 수 없습니다.");
		}
	}
	
	// 입금
	// 마이너스 입금하면 "-10000원 입금 불가" 예외메시지 출력
	public void deposit() {
		try {
			System.out.println("***입금***");
			System.out.print("입금액 >>> ");
			long money = sc.nextLong();
			if(money < 0)
				throw new RuntimeException(money + "원 입금 불가");
			balance += money;
			System.out.println("입금 성공!");
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	// 출금
	// 마이너스 출금하면 "-10000원 출금 불가" 예외메시지 출력
	// 잔고보다 큰 금액을 출금하면 "잔고 부족" 예외메시지 출력
	public void withdrawal() {
		try {
			System.out.println("***출금***");
			System.out.print("출금액 >>> ");
			long money = sc.nextLong();
			if(money < 0)
				throw new RuntimeException(money + "원 출금 불가");
			else if(money > balance)
				throw new RuntimeException("잔고 부족");
			balance -= money;
			System.out.println("출금 성공!");
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 이체를 위한 입금
	private void deposit(long money) {  // long money : 입금하려는 금액
		try {
			if(money < 0)
				throw new RuntimeException(money + "원 입금 불가");
			balance += money;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 이체를 위한 출금
	// 반환타입 long       : 실제로 출금된 금액
	// 매개변수 long money : 출금하려는 금액
	private long withdrawal(long money) {
		try {
			if(money < 0) {
				throw new RuntimeException(money + "원 출금 불가");
			} else if(money > balance)
				throw new RuntimeException("잔고 부족");
			balance -= money;
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
			money = 0;
		}
		return money;
	}
	
	// 이체
	// 출금액 -> 입금액
	public void transfer() {
		System.out.println("***이체***");
		System.out.print("이체할 예금주 >>> ");
		String owner = sc.next();
		for(int i = 0; i < idx; i++) {
			if(owner.equals(accounts[i].owner)) {
				System.out.print("이체할 금액 >>> ");
				long money = sc.nextLong();
				accounts[i].deposit(withdrawal(money));
				return;
			}
		}
		System.out.println(owner + " 예금주가 없습니다.");
	}
	
	// 조회
	public void inquiry() {
		System.out.println("***조회***");
		System.out.println("예금주 " + owner + " 계좌번호 " + accNo + " 잔고 " + balance + "원");
		System.out.println("자주쓰는계좌목록");
		for(int i = 0; i < idx; i++) {
			System.out.println("예금주 " + accounts[i].owner + " 계좌번호 " + accounts[i].accNo + " 잔고 " + accounts[i].balance + "원");
		}
	}
	
	// 메뉴
	public void menu() {
		System.out.println("===========");
		System.out.println("1. 계좌등록");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 이체");
		System.out.println("5. 조회");
		System.out.println("0. 종료");
		System.out.println("===========");
	}
	
	// 계좌관리실행
	public void manage() {
		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,5,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addAccount(); break;
				case 2: deposit(); break;
				case 3: withdrawal(); break;
				case 4: transfer(); break;
				case 5: inquiry(); break;
				case 0: System.out.println("계좌관리종료"); return;
				default: System.out.println("잘못된 선택입니다. 다시 시도하세요.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("작업 선택은 정수(1,2,3,4,5,0) 입력입니다.");
			}
		}
	}
	
}
