package com.uleehub.webservice.soap.security;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-2
 * <p>Version: 1.0
 */

@WebService
public interface UserService {
	@WebMethod
	@WebResult
	List<User> list();

}
