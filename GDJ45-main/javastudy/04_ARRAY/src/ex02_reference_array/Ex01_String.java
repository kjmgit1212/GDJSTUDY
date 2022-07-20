package ex02_reference_array;

public class Ex01_String {

	public static void main(String[] args) {
		
		String[] menu = new String[3];
		
		menu[0] = "김치찌개";
		menu[1] = "된장찌개";
		menu[2] = "부대찌개";
		
		for(int i = 0; i < menu.length; i++)
			System.out.println(menu[i]);

	}

}
