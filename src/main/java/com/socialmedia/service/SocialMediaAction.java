package com.socialmedia.service;

import java.util.List;

/**
 * Interface to obtain the functionality
 *
 */
public interface SocialMediaAction {

	/**
	 * To create a new post 
	 * 
	 * @param userId
	 *            {@link String}
	 * @param postId
	 *            {@link Integer}
	 * @param content
	 *            {@link content}
	 * @return {@link boolean}
	 */
	boolean createPost(String userId, Integer postId, String content);

	/**
	 * To get the news feed based on the userId
	 * 
	 * @param userId
	 *            {@link String}
	 * @return {@link List<String>}
	 */
	List<String> getNewsFeed(String userId);

	/**
	 * To Follow the other users
	 * 
	 * @param followerId
	 *            {@link String}
	 * @param followeeId
	 *            {@link String}
	 * @return {@link boolean}
	 */
	boolean follow(String followerId, String followeeId);

	/**
	 * To unfollow the users
	 * 
	 * @param followerId
	 *            {@link String}
	 * @param followeeId
	 *            {@link String
	 * @return {@link boolean}
	 */
	boolean unfollow(String followerId, String followeeId);
	
	/**
	 * To add the user in the set, to identify whether the user is available or
	 * not (assumption)
	 * 
	 * @param userId
	 *            {@link userId}
	 */
	void addUser(String userId);
	
	/**
	 * To check whether the user exists in the set (assumption)
	 * 
	 * @param userId
	 *            {@link String}
	 * @return{@link boolean}
	 */
	boolean checkUser(String userId);
	
	/**
	 * To print the post in the console
	 * 
	 * @param posts
	 *            {@link List<String>}
	 */
	void printPost(List<String> posts);

}
