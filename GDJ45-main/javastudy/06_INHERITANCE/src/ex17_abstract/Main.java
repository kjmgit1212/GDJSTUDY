package ex17_abstract;

public class Main {

	public static void main(String[] args) {
		
		GameUnit unit1 = new Tank("탱크");
		GameUnit unit2 = new Marine("마린");
		
		System.out.println(unit1.getName() + " 에너지 " + unit1.getEnergy() + " 공격력 " + unit1.getPower());
		System.out.println(unit2.getName() + " 에너지 " + unit2.getEnergy() + " 공격력 " + unit2.getPower());

		System.out.println("===전투 시작===");
		
		// 공격 차례
		boolean myTurn = Math.random() < 0.5;
		
		// 두 유닛이 모두 살아있으면 반복
		while( unit1.isAlive() && unit2.isAlive() ) {  // while( unit1.getEnergy() > 0 && unit2.getEnergy() > 0 ) {
			if (myTurn) {
				System.out.println(unit1.getName() + "의 공격!");
				unit1.attack(unit2);  // unit1이 unit2를 공격한다.
				myTurn = false;
			} else {
				System.out.println(unit2.getName() + "의 공격!");
				unit2.attack(unit1);
				myTurn = true;
			}
		}
		
		System.out.println("===전투 종료===");
		
		// 승자는?
		if(unit1.isAlive()) {
			System.out.println(unit1.getName() + "의 승리! 남은 에너지 " + unit1.getEnergy());
		} else {
			System.out.println(unit2.getName() + "의 승리! 남은 에너지 " + unit2.getEnergy());
		}
		
	}

}
