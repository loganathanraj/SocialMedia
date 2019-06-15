package com.socialmedia.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.socialmedia.api.PostDetails;
import com.socialmedia.service.SocialMediaAction;

/**
 * Service Implementation class to implement the method on SocialMediaAction
 *
 */
public class SocialMediaActionImpl implements SocialMediaAction {

	private Map<Integer, PostDetails> postDetails = new LinkedHashMap<>();
	private Map<String, List<String>> followDetails = new HashMap<>();
	private Set<String> userSet = new HashSet<>();

	/* 
	 * Method to create a post
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#createPost(java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean createPost(String userId, Integer postId, String content) {
		PostDetails post = new PostDetails(userId, postId, content, new Date());
		postDetails.put(postId, post);
		return true;
	}

	/* 
	 * Method to get the Latest News Feed based on the user and follower
	 * 
	 *  @see com.SocialMedia.Service.SocialMediaAction#getNewsFeed(java.lang.String)
	 */
	
	@Override
	public List<String> getNewsFeed(String userId) {
		List<String> followerList = followDetails.getOrDefault(userId, new ArrayList<>());
		int count = 0;
		Map<Integer, PostDetails> post = postDetails.entrySet().stream()
				.sorted(Map.Entry.<Integer, PostDetails>comparingByValue(
						Comparator.comparing(PostDetails::getPostedDate).reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (k, v) -> k, LinkedHashMap::new));
		List<String> feedData = new ArrayList<>();
		for (Entry<Integer, PostDetails> val : post.entrySet()) {
			if (userId.equals(val.getValue().getUserId()) || (followerList.contains(val.getValue().getUserId()))) {
				feedData.add(val.getValue().getContent());
				count++;
			}
			if (count == 20) {
				break;
			}
		}
		return feedData;
	}

	/* 
	 * Method to follow other users
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#follow(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean follow(String followerId, String followeeId) {
		if (followerId.equals(followeeId)) {
			System.out.println("User Id and Followe Id are Same");
			return false;
		}
		if (!checkUser(followerId)) {
			System.out.println("User Id Not Found");
			return false;
		} else {
			List<String> followerList = new ArrayList<>();
			followerList = followDetails.get(followeeId);
			if (followerList == null || followerList.isEmpty()) {
				followerList = new ArrayList<>();
			}
			followerList.add(followerId);
			followDetails.put(followeeId, followerList);
			return true;
		}
	}

	/* 
	 * Method to unfollow others
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#unfollow(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean unfollow(String followerId, String followeeId) {
		if (followerId.equals(followeeId)) {
			System.out.println("User Id and Followe Id are Same");
			return false;
		}
		if (!checkUser(followerId)) {
			System.out.println("User Id Not Found");
			return false;
		} else {
			List<String> followerList = followDetails.get(followeeId);
			if (followerList == null || followerList.isEmpty()) {
				System.out.println("Follower List is Empty");
				return false;
			}
			followerList.remove(followerId);
			followDetails.put(followeeId, followerList);
			return true;
		}

	}

	/* 
	 * To add the new user in the set 
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#addUser(java.lang.String)
	 */
	@Override
	public void addUser(String userId) {
		userSet.add(userId);

	}

	/* 
	 * To check whether the user already exists or not
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#checkUser(java.lang.String)
	 */
	@Override
	public boolean checkUser(String userId) {
		if (userSet.contains(userId)) {
			return true;
		}
		return false;
	}

	/* 
	 * To print the post in the console
	 * 
	 * @see com.SocialMedia.Service.SocialMediaAction#printPost(java.util.List)
	 */
	@Override
	public void printPost(List<String> posts) {
		posts.stream().forEach(System.out::println);
	}

}
