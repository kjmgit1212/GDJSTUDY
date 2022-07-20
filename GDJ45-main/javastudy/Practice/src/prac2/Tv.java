package prac2;

public class Tv {

	// field
	private Remocon remocon;  // null

	// method
	public Remocon getRemocon() {
		return remocon;
	}
	public void setRemocon(Remocon remocon) {
		this.remocon = remocon;
	}
	public void chUp() {
		remocon.chUp();
	}
	public void chDown() {
		remocon.chDown();
	}
	public void volUp() {
		remocon.volUp();
	}
	public void volDown() {
		remocon.volDown();
	}
	
}
