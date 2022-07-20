package ex20_interface;

public class Person {

	public void feed(Pet pet, String food) {
		System.out.println(pet.getName() + "에게 " + food + "를 준다.");
	}
	
	public void walk(Walkable pet) {
		System.out.println(((Pet)pet).getName() + "와 산책하기.");
	}
	
}
