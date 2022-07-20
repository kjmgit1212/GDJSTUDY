package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OpenAPIService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
