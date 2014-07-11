package com.uleehub.webservice.soap;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-1
 * <p>Version: 1.0
 */
public class ServerPasswordCallback implements CallbackHandler {  
	  
    private static final String BUNDLE_LOCATION = "ws_security.security";
    private static final String PASSWORD_PROPERTY_IDENTIFIER = "auth.manager.identifier";  
    private static final String PASSWORD_PROPERTY_NAME = "auth.manager.password";  
    
    private static String identifier;  
    private static String password;  
    static {  
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_LOCATION);
        identifier = bundle.getString(PASSWORD_PROPERTY_IDENTIFIER);  
        password = bundle.getString(PASSWORD_PROPERTY_NAME);  
    }  
  
    public void handle(Callback[] callbacks) throws IOException,  
            UnsupportedCallbackException {  
  
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];  
  
        // Set the password on the callback. This will be compared to the  
        // password which was sent from the client.  
        // We can call pc.getIdentifer() right here to check the username  
        // if we want each client to have it's own password.  
        if (pc.getIdentifier().equalsIgnoreCase(identifier)) {  
        	/*cxf2.4之后最新版的不用取密码对比，设置密码就行了*/
        	pc.setPassword("admin");
        }  
        else  
        {  
            throw new SecurityException("the user does not exits");  
        }  
  
    }  
    public static  void main(String args[]){
    	final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_LOCATION);
        identifier = bundle.getString(PASSWORD_PROPERTY_IDENTIFIER);  
        password = bundle.getString(PASSWORD_PROPERTY_NAME);  
        System.out.println(identifier+ "  "+password);
    }
}  
