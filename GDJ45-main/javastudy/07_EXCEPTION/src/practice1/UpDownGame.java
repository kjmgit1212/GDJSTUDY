package practice1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {

	// 1~100 사이 난수를 하나 발생시키고,
	// 사용자가 해당 난수를 맞히는 게임
	
	// 게임이 끝나면 몇 번만에 맞혔는지 출력해 주고,
	// 1~100 사이를 벗어난 값을 입력하면
	// "1~100 사이 정수만 입력할 수 있습니다." 메시지 출력해 주기
	
	private int rand;   // 발생한 난수
	private int count;  // 시도 횟수
	private Scanner sc;
	
	public UpDownGame() {
		rand = (int)(Math.random() * 100 + 1);
		count = 0;
		sc = new Scanner(System.in);
	}
	
	// 입력 메소드
	public int input() {
		count++;
		System.out.print("1~100 사이 입력 >>> ");
		int n = sc.nextInt();
		if (n < 1 || n > 100)
			throw new RuntimeException();
		return n;
	}
	
	// 게임 실행 메소드
	public void play() {
		
		while(true) {
			
			try {
			
				int n = input();
			
				if (n < rand) {
					System.out.println("Up!");
				} else if(n > rand) {
					System.out.println("Down!");
				} else {
					System.out.println(rand + " 정답입니다. " + count + "번만에 맞혔습니다");
					sc.close();
					break;
				}
				
			} catch (InputMismatchException e) {
				// sc.nextInt()로 입력되지 못한 입력이 System.in에 남아 있어서 제거해야 한다.
				sc.next();
				System.out.println("정수만 입력할 수 있습니다.");
			} catch (RuntimeException e) {
				System.out.println("1~100 사이 정수만 입력할 수 있습니다.");
			}
			
		}  // while(true)
		
	}  // play()
	
}
