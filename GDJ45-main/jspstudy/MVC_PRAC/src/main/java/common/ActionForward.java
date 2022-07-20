package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionForward {
	private boolean isRedirect;
	private String view;
}
