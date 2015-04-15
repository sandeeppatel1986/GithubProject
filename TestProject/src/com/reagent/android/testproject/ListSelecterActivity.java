package com.reagent.android.testproject;

import com.reagent.android.testproject.nativelistview.GitHubRepoCommetDeatilActivity;
import com.reagent.android.testproject.weblistview.GitHubRepoCommetDeatilWebViewActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * This Activity used for select Native or Webview list Activity
 * @author Sandeep_PC
 *
 */
public class ListSelecterActivity extends BaseActivity implements OnClickListener{

	private Context mContext;
	private View view;
	private LayoutInflater inflater;
	private Button btnNativeList,btnWebViewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
		setListener();
	}

	/**
	 * Initialize view & objects
	 */
	private void initialize (){
		mContext = this;
		inflater = LayoutInflater.from(mContext);
		view = inflater.inflate(R.layout.layout_activitylist,
				getMiddleContent());
		setModuleTitle(getString(R.string.module_title));
		btnNativeList = (Button)view.findViewById(R.id.btnNativView);
		btnWebViewList = (Button)view.findViewById(R.id.btnWebView);
	}

	/**
	 * Set Activity object Listener
	 */
	private void setListener(){
		btnNativeList.setOnClickListener(this);
		btnWebViewList.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnNativView:
			startActivity(new Intent(mContext, GitHubRepoCommetDeatilActivity.class));
			break;
		case R.id.btnWebView:
			startActivity(new Intent(mContext, GitHubRepoCommetDeatilWebViewActivity.class));
			break;
		default:
			break;
		}
	}


}
