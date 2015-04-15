package com.reagent.android.testproject.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.reagent.android.testproject.R;
import com.reagent.android.testproject.vo.RepoCommitVO;

/**
 * BaseAdapter for Set Repository Commit Detail List
 * @author Sandeep_PC
 *
 */
public class CommitListBaseAdapter extends BaseAdapter {

	private ArrayList<RepoCommitVO> mCommitListData = new ArrayList<RepoCommitVO>();
	private LayoutInflater inflater;
	private Context mContext;

	/**
	 * Constructor For BaseAdapter
	 * @param mContext   Activity Context object
	 * @param mCommitListData   ArrayList For RepoCommitVO
	 */
	public CommitListBaseAdapter(Context mContext, ArrayList<RepoCommitVO> mCommitListData) {
		this.mCommitListData = mCommitListData;
		this.mContext = mContext;
		inflater = LayoutInflater.from(this.mContext);    
	}
	
	/**
	 * setList For RepoCommitVO
	 * @param mCommitListData
	 */
	public void setList( ArrayList<RepoCommitVO> mCommitListData) {
		this.mCommitListData = mCommitListData;
	}
	
	@Override
	public int getCount() {
		return mCommitListData.size();
	}

	@Override
	public RepoCommitVO getItem(int position) {
		return mCommitListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHolder mViewHolder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.layout_list_item, null);
			mViewHolder = new MyViewHolder();

			mViewHolder.tvCommiterName = (TextView) convertView.findViewById(R.id.tvCommiterName);
			mViewHolder.tvCommitDate = (TextView) convertView.findViewById(R.id.tvCommitDate);
			mViewHolder.tvCommitMessage = (TextView) convertView.findViewById(R.id.tvCommitMessage);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (MyViewHolder) convertView.getTag();
		}
		
		mViewHolder.tvCommiterName.setText(mCommitListData.get(position).getCommiterName());
		mViewHolder.tvCommitDate.setText("Date: "+mCommitListData.get(position).getCommiteDate());
		mViewHolder.tvCommitMessage.setText("Message: "+mCommitListData.get(position).getCommiteMessage());
		return convertView;
	}

	private class MyViewHolder {
		TextView tvCommiterName, tvCommitDate,tvCommitMessage;
	}

}
