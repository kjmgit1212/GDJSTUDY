package ex17_abstract;

public class Marine extends GameUnit {

	public Marine(String name) {
		super(name, 50, 5);  // 에너지 50 공격력 5
	}
	
	@Override
	public void attack(GameUnit unit) {
		// 30% 확률로 한 번에 unit을 죽인다.
		if(Math.random() < 0.3) {
			unit.setEnergy(0);
			System.out.println(unit.getName() + "이 KO되었다.");
		} else {
			unit.setEnergy(unit.getEnergy() - this.getPower());
			System.out.println(unit.getName() + "의 남은 에너지 " + unit.getEnergy());
		}
	}
	
}
