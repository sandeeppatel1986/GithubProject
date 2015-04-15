package com.reagent.android.testproject.weblistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.reagent.android.testproject.BaseActivity;
import com.reagent.android.testproject.R;
import com.reagent.android.testproject.util.NetworkUtil;
/**
 * This Activity used for list all commit entries of github repo in webview
 * @author Sandeep_PC
 *
 */
public class GitHubRepoCommetDeatilWebViewActivity extends BaseActivity {

	private Context mContext;
	private WebView webView;
	private View view;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}

	/**
	 * Initialize view & objects
	 */
	@SuppressLint("SetJavaScriptEnabled")
	private void initialize (){
		mContext = this;
		inflater = LayoutInflater.from(mContext);
		view = inflater.inflate(R.layout.layout_web,
				getMiddleContent());
		webView = (WebView) view.findViewById(R.id.webview);
	    WebSettings webSettings = webView.getSettings();
	    webSettings.setJavaScriptEnabled(true);
	   
	    if(NetworkUtil.isOnline(mContext)){
	    	 webView.loadUrl("file:///android_asset/index.html");
		}else{
			showToast(getString(R.string.network_error));
		}
	    
	    setModuleTitle(getString(R.string.module_title_web));
	}


}
