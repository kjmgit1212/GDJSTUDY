package ex07_exception_class;

// Exception 클래스를 상속 받으면 예외 클래스가 된다.

public class EmployeeException extends Exception {

	private int errorCode;
	
	public EmployeeException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}