package com.reagent.android.testproject.http;


/**
 * 
 * @author Sandeep
 *
 */
public interface HttpCallback {
   
	/**
	 * Callback method for http response. called when an http response is received.
	 * @param response
	 * @param action
	 */
    public void onResponse(String response,int action);
    
}