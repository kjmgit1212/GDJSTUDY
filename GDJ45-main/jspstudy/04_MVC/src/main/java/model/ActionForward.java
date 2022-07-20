package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionForward {
	private boolean isRedirect;  // redirect이면 true, forward이면 false (디폴트는 forward)
	private String view;
}
