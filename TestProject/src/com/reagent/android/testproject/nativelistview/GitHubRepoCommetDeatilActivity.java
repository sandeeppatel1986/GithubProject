package com.reagent.android.testproject.nativelistview;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.reagent.android.testproject.BaseActivity;
import com.reagent.android.testproject.R;
import com.reagent.android.testproject.adapter.CommitListBaseAdapter;
import com.reagent.android.testproject.http.HttpCallback;
import com.reagent.android.testproject.http.HttpRequest;
import com.reagent.android.testproject.util.NetworkUtil;
import com.reagent.android.testproject.vo.RepoCommitVO;
/**
 * This Activity used for list all commit entries of github repo on native listview
 * @author Sandeep_PC
 *
 */
public class GitHubRepoCommetDeatilActivity extends BaseActivity implements HttpCallback{

	private ListView lvRepoCommiteList;
	private CommitListBaseAdapter mAdapter;
	private Context mContext;
	private Handler mHandler;
	private ArrayList<RepoCommitVO> mCommitListData;
	private View view;
	private LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize();
		getWebServiceResponse();
	}

	/**
	 * Initialize view & objects
	 */
	private void initialize (){
		mContext = this;
		inflater = LayoutInflater.from(mContext);
		view = inflater.inflate(R.layout.layout_native_list,
				getMiddleContent());
		lvRepoCommiteList = (ListView)view.findViewById(R.id.lvRepoCommiteList);
		mHandler = new Handler();
		mCommitListData = new ArrayList<RepoCommitVO>();
		setModuleTitle(getString(R.string.module_title_native));
	}


	/**
	 * Call WebServie
	 */
	private void getWebServiceResponse(){
		if(NetworkUtil.isOnline(mContext)){
			showProgress("Loading...");
			HttpRequest httpGet = new HttpRequest(getString(R.string.base_url), null, 1, this);
			new Thread(httpGet).start();
		}else{
			showToast(getString(R.string.network_error));
		}
	}

	/**
	 *   Callback method for http response. called when an http resounce is received.
	 *   @param  response
	 *          response from server
	 *   @param  action
	 *          action id for request url
	 */
	@Override
	public void onResponse(String response, int action) {
		if(action == 1){
			if(response != null){
				try {
					JSONArray jArray = new JSONArray(response);

					for (int i = 0; i < jArray.length(); i++) {

						JSONObject jObj = jArray.getJSONObject(i);
						JSONObject jCommitObj = jObj.getJSONObject("commit");
						JSONObject jCommiterObj = jCommitObj.getJSONObject("committer");
						
						RepoCommitVO objRepCommit = new RepoCommitVO();
						objRepCommit.setCommiterName(jCommiterObj.optString("name", "Committer Name"));
						objRepCommit.setCommiteDate(jCommiterObj.optString("date", "Commit Date"));
						objRepCommit.setCommiteMessage(jCommitObj.optString("message", "Commit message"));
						
						mCommitListData.add(objRepCommit);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						stopProgress();
						if(mAdapter == null){
							mAdapter = new CommitListBaseAdapter(mContext, mCommitListData);
							lvRepoCommiteList.setAdapter(mAdapter);
						}else{
							mAdapter.setList(mCommitListData);
							mAdapter.notifyDataSetChanged();
						}
					}
				});
			}
		}
	}

}
