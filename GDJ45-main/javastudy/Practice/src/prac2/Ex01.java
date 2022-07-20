package prac2;

public class Ex01 {

	public static void main(String[] args) {
		
		Remocon remocon = new Remocon();
		Tv tv = new Tv();
		
		tv.setRemocon(remocon);
		
		tv.chUp();
		tv.chDown();
		
		tv.volUp();
		tv.volDown();

	}

}
