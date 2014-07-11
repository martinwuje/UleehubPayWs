package com.uleehub.webservice.soap.security;
import java.io.IOException;  

import javax.security.auth.callback.Callback;  
import javax.security.auth.callback.CallbackHandler;  
import javax.security.auth.callback.UnsupportedCallbackException;  

import org.apache.ws.security.WSPasswordCallback;  

/**
 * <p>User: mtwu
 * <p>Date: 2014-7-2
 * <p>Version: 1.0
 */
public class WsClientAuthHandler implements CallbackHandler {  
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {  
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];  
        int usage = callback.getUsage();  
        System.out.println("identifier: " + callback.getIdentifier());  
        System.out.println("usage: " + callback.getUsage());  
        if (usage == WSPasswordCallback.USERNAME_TOKEN) {  
            callback.setPassword("admin");  
        }  
    }  
}  
