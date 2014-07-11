package com.uleehub.webservice.soap.security;
/**
 * <p>User: mtwu
 * <p>Date: 2014-7-2
 * <p>Version: 1.0
 */
import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.security.auth.callback.Callback;  
import javax.security.auth.callback.CallbackHandler;  
import javax.security.auth.callback.UnsupportedCallbackException;  
  
import org.apache.ws.security.WSPasswordCallback;  
  
public class WsServerAuthHandler implements CallbackHandler {  
  
    // key is username, value is password  
    private Map<String, String> users;  
  
    public WsServerAuthHandler() {  
        users = new HashMap<String, String>();  
        users.put("admin", "admin");  
    }  
  
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {  
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];  
        String id = callback.getIdentifier();  
        if (users.containsKey(id)) {  
        	/*cxf2.4之后最新版的不用取密码对比，设置密码就行了*/
//            if (!callback.getPassword().equals(users.get(id))) {  
//                throw new SecurityException("Incorrect password.");
//            }  
        	callback.setPassword("admin");
        } else {  
            throw new SecurityException("Invalid user.");  
        }  
    }  
}  
