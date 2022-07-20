package prac2;

public class Remocon {

	// field
	private int ch;   // 0, 0~11
	private int vol;  // 0, 0~5
	private final int MAX_CH = 11;
	private final int MAX_VOL = 5;
	
	// method
	public void chUp() {
		if(ch == MAX_CH) {
			ch = 0;
			return;
		}
		ch++;
	}
	public void chDown() {
		if(ch == 0) {
			ch = MAX_CH;
			return;
		}
		ch--;
	}
	public void volUp() {
		if(vol < MAX_VOL)
			vol++;
	}
	public void volDown() {
		if(vol > 0)
			vol--;
	}
	
}
