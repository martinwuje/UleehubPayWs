package com.uleehub.webservice.soap.pay.common;

import org.slf4j.Logger;

import com.uleehub.webservice.soap.pay.response.base.WSResult;

/**
 * 
 * <p>User: mkwu
 * <p>Date: 2014-6-26
 * <p>Version: 1.0
 */
public abstract class BaseWebService {
	
	public abstract Logger getLogger();
	public <T extends WSResult> T handleParameterError(T result, Exception e, String message) {
		getLogger().error(message, e.getMessage());
		result.setError(WSResult.PARAMETER_ERROR, message);
		return result;
	}

	public <T extends WSResult> T handleParameterError(T result, Exception e) {
		getLogger().error(e.getMessage());
		result.setError(WSResult.PARAMETER_ERROR, e.getMessage());
		return result;
	}

	public <T extends WSResult> T handleGeneralError(T result, Exception e) {
		getLogger().error(e.getMessage());
		result.setDefaultError();
		result.setMessage(e.getMessage());
		return result;
	}
}
