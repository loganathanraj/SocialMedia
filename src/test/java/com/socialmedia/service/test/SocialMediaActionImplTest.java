package com.socialmedia.service.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.socialmedia.service.SocialMediaAction;
import com.socialmedia.service.impl.SocialMediaActionImpl;


public class SocialMediaActionImplTest {

	SocialMediaAction media;
	
	@Before
	public void setup(){
		media = new SocialMediaActionImpl();
		media.addUser("1");
		media.addUser("2");
		media.addUser("3");
	}
	
	@Test
	public void testChecUser(){
		boolean value = media.checkUser("1");
		org.junit.Assert.assertTrue(value);
		value = media.checkUser("4");
		org.junit.Assert.assertFalse(value);
	}
	
	@Test
	public void testCreatePost() {
		media.createPost("1", 1, "Hello How are you");
		List<String> post = media.getNewsFeed("1");
		org.junit.Assert.assertEquals(post.get(0), "Hello How are you");
	}
	
	@Test
	public void testFollow() {
		boolean value = media.follow("1", "2");
		org.junit.Assert.assertTrue(value);
		value = media.follow("4", "2");
		org.junit.Assert.assertFalse(value);
		value = media.follow("2", "2");
		org.junit.Assert.assertFalse(value);
	}
	
	@Test
	public void testUnFollow() {
		media.follow("1", "2");
		boolean value = media.unfollow("1", "2");
		org.junit.Assert.assertTrue(value);
		value = media.unfollow("4", "2");
		org.junit.Assert.assertFalse(value);
		value = media.unfollow("2", "2");
		org.junit.Assert.assertFalse(value);
	}
	
	@Test
	public void testGetNewsFeed() {
		media.createPost("2", 30, "24");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		media.createPost("1", 1, "1");
		media.createPost("1", 2, "2");
		media.createPost("1", 3, "3");
		media.createPost("1", 4, "4");
		media.createPost("1", 5, "5");
		media.createPost("1", 6, "6");
		media.createPost("1", 7, "7");
		media.createPost("1", 8, "8");
		media.createPost("1", 9, "9");
		media.createPost("1", 10, "10");
		media.createPost("1", 11, "11");
		media.createPost("1", 12, "12");
		media.createPost("1", 13, "13");
		media.createPost("1", 14, "14");
		media.createPost("3", 15, "15");
		media.createPost("1", 16, "16");
		media.createPost("1", 17, "17");
		media.createPost("2", 18, "18");
		media.createPost("1", 19, "19");
		media.createPost("1", 20, "20");
		media.createPost("1", 21, "21");
		media.createPost("2", 22, "22");
		media.createPost("2", 23, "23");
		media.follow("2", "1");
		List<String> posts = media.getNewsFeed("1");
		org.junit.Assert.assertEquals(posts.size(), 20);
		for(String post:posts){
			org.junit.Assert.assertNotEquals(post,"15");
			org.junit.Assert.assertNotEquals(post,"24");
		}
	}
	
}