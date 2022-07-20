package quiz01;

public class SchoolMain {

	public static void main(String[] args) {
		
		School school1 = new School("철산초등학교", "철산동");
		System.out.println(school1.getName());      // 철산초등학교
		System.out.println(school1.getLocation());  // 철산동
		
		School school2 = new School();
		school2.setName("소하초등학교");
		school2.setLocation("소하동");
		System.out.println(school2.getName());      // 소하초등학교
		System.out.println(school2.getLocation());  // 소하동
		
	}

}
