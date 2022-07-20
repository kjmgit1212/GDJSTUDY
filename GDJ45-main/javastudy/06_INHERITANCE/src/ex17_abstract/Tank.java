package ex17_abstract;

public class Tank extends GameUnit {

	public Tank(String name) {
		super(name, 100, 10);  // 탱크 에너지 100 공격력 10
	}
	
	@Override
	public void attack(GameUnit unit) {
		// 5% 확률로 한 번에 unit을 죽인다.
		if(Math.random() < 0.05) {
			unit.setEnergy(0);
			System.out.println(unit.getName() + "이 KO되었다.");
		} else {
			unit.setEnergy(unit.getEnergy() - this.getPower());
			System.out.println(unit.getName() + "의 남은 에너지 " + unit.getEnergy());
		}
	}
	
}
