package practice3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Zoo {
	
	private Scanner sc;
	private Animal[] animals;
	private int idx;
	
	public Zoo(int count) {
		sc = new Scanner(System.in);
		animals = new Animal[count];
	}
	
	// 추가
	// 배열의 범위를 벗어나는 경우 "더 이상 추가할 수 없습니다."
	public void addAnimal() throws RuntimeException {
		if(idx == animals.length)
			throw new RuntimeException("더 이상 추가할 수 없습니다.");
		System.out.print("동물 이름 >>> ");
		String name = sc.next();
		if(Math.random() < 0.5) {
			animals[idx++] = new Lion(name);
		} else {
			animals[idx++] = new Tiger(name);
		}
		System.out.println(name + " 동물이 추가되었습니다.");
	}
	
	// 삭제
	// 배열이 비어 있는 경우 "등록된 동물이 없습니다."
	public void removeAnimal() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다.");
		System.out.print("삭제할 동물 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < idx; i++) {
			if(name.equals(animals[i].getName())) {
				System.out.println(name + " 동물을 삭제합니다.");
				System.arraycopy(animals, i + 1, animals, i, idx - 1 - i);
				animals[--idx] = null;
				return;
			}
		}
		System.out.println(name + " 동물이 없습니다.");
	}
	
	// 조회
	// 배열이 비어 있는 경우 "등록된 동물이 없습니다."
	public void findAnimal() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다.");
		System.out.print("조회할 동물 이름 >>> ");
		String name = sc.next();
		for(int i = 0; i < idx; i++) {
			if(name.equals(animals[i].getName())) {
				System.out.println("조회된 동물 " + animals[i]);
				return;
			}
		}
		System.out.println(name + " 동물이 없습니다.");
	}
	
	// 전체
	// 배열이 비어 있는 경우 "등록된 동물이 없습니다."
	public void findAllAnimals() throws RuntimeException {
		if(idx == 0)
			throw new RuntimeException("등록된 동물이 없습니다.");
		System.out.println("===전체 동물 목록===");
		for(int i = 0; i < idx; i++)
			System.out.println((i + 1) + "번째 동물 " + animals[i]);
	}
	
	public void menu() {
		System.out.println("************");
		System.out.println("** 1.추가 **");
		System.out.println("** 2.삭제 **");
		System.out.println("** 3.조회 **");
		System.out.println("** 4.전체 **");
		System.out.println("** 0.종료 **");
		System.out.println("************");
	}
	
	public void manage() {
		
		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addAnimal(); break;
				case 2: removeAnimal(); break;
				case 3: findAnimal(); break;
				case 4: findAllAnimals(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default: System.out.println("잘못된 선택입니다.");
				}
			} catch(InputMismatchException e) {
				System.out.println("선택은 1,2,3,4,0 중 하나입니다.");
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
}
