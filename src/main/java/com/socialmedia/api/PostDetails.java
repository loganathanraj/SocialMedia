package com.socialmedia.api;

import java.util.Date;

/**
 * POJO class to have getter and setter method
 *
 */
public class PostDetails {

	private String userId;
	private Integer postId;
	private String content;
	private Date postedDate;

	public PostDetails(String userId, Integer postId, String content, Date postedDate) {
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.postedDate = postedDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
}
