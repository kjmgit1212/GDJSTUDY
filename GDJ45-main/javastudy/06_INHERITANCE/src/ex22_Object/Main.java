package ex22_Object;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		// user1과 user2는 다른 참조값입니다
		
		// 변수            데이터       참조(주소)
		//                 "admin"      1
		//                 "123456"     2
		// user1.userId    1            10
		// user1.userPw    2
		// user2.userId    1            20
		// user2.userPw    2
		// user1           10
		// user2           20
		
		// user1 == user2 : false
		// user1.userId == user2.userId : true
		
		User user1 = new User("admin", "123456");
		User user2 = new User("admin", "123456");
		
		if(user1.equals(user2))
			System.out.println("동일한 회원");
		else
			System.out.println("다른 회원");
		
		String strUser1 = user1.toString();
		System.out.println(strUser1);
		
		String strUser2 = user2.toString();
		System.out.println(strUser2);
		
		// toString 메소드는 생략할 수 있다.
		System.out.println(user1);
		System.out.println(user2);
		
		User[] users = new User[2];
		users[0] = user1;
		users[1] = user2;
		
		System.out.println(Arrays.toString(users));

	}

}
