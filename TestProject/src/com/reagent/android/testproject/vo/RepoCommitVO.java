package com.reagent.android.testproject.vo;
/**
 * POJO for Repository Commit Detail
 * @author Sandeep_PC
 *
 */
public class RepoCommitVO {

	private String commiterName;
	private String commiteMessage;
	private String commiteDate;
	
	public RepoCommitVO(){
		
	}
	public RepoCommitVO(String commiterName,String commiteMessage,String commiteDate) {
		this.commiterName = commiterName;
		this.commiteMessage = commiteMessage;
		this.commiteDate = commiteDate;
	}
	
	public String getCommiterName() {
		return commiterName;
	}
	public void setCommiterName(String commiterName) {
		this.commiterName = commiterName;
	}
	public String getCommiteMessage() {
		return commiteMessage;
	}
	public void setCommiteMessage(String commiteMessage) {
		this.commiteMessage = commiteMessage;
	}
	public String getCommiteDate() {
		return commiteDate;
	}
	public void setCommiteDate(String commiteDate) {
		this.commiteDate = commiteDate;
	}
}
