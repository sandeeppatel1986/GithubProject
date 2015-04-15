package com.reagent.android.testproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * This Activity is BaseActivity for all Activitys
 * @author Sandeep_PC
 *
 */
public class BaseActivity extends Activity {

	private ProgressDialog pd;
	private TextView tvModuleTitle;
	private LinearLayout content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
	}

	/**
	 * Initialize view & objects
	 */
	private void initialize (){
		setContentView(R.layout.layout_base);
		tvModuleTitle = (TextView)findViewById(R.id.tvModuleTitle);
		content = (LinearLayout) findViewById(R.id.middlelayout);
	}

	/**
	 * Use this method to get reference to the Middle Content of the
	 * BaseActivity. Pass this reference as {@link ViewGroup} <code>root</code>
	 * to LayoutInflater.inflat() method.
	 * 
	 * @param view
	 *            to be set in the middle
	 */
	protected LinearLayout getMiddleContent() {
		return content;
	}
	
	/** 
	 * Use this method for set ModuleTitle
	 */
	protected void setModuleTitle(String mTitle){
		tvModuleTitle.setText(mTitle);
	}

	/**
	 * ShowToast Message Notification
	 * 
	 * @param msg
	 *            Toast Title Message
	 */
	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ShowToast Message Notification
	 * 
	 * @param msg
	 *            Toast Title Message using String Resource
	 */
	protected void showToast(int id) {
		Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show();
	}

	/**
	 * showProgress for show Dialog
	 * 
	 * @param msg
	 *            Title Message For Progress Dialog using String
	 */
	protected void showProgress(String msg) {
		if (pd == null) {
			pd = new ProgressDialog(this);
			pd.setCancelable(false);
		}
		pd.setMessage(msg);
		pd.show();
	}

	/**
	 * Cancel Progress Dialog
	 */
	protected void stopProgress() {
		if (pd != null) {
			pd.cancel();
		}
	}

}
