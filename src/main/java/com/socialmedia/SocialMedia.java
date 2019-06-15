package com.socialmedia;

import java.util.List;
import java.util.Scanner;

import com.socialmedia.service.SocialMediaAction;
import com.socialmedia.service.impl.SocialMediaActionImpl;

/**
 * Main method to execute the socialMedia Application
 *
 */
public class SocialMedia {

	static int i = 0;

	public static void main(String[] args) {
		// try with resource block to close the scanner to eliminate the memory leak
		try (Scanner userInput = new Scanner(System.in)) {
			SocialMediaAction media = new SocialMediaActionImpl();
			String followerId = null;
			String content = null;
			outer: while (true) {
				System.out.println("Please Enter the your id");
				String userId = userInput.nextLine();
				// To check whether the user is available or not
				if (!media.checkUser(userId)) {
					media.addUser(userId);
				}
				System.out.println("1.createPost\t2.getPost\t3.Follow\t4.UnFollow");
				String n = userInput.nextLine();
				switch (n) {
				case "1":
					// To Enter the content to the post
					System.out.println("Please Enter the content");
					content = userInput.nextLine();
					media.createPost(userId, ++i, content);
					break;
				case "2":
					// To Get the post details
					List<String> posts = media.getNewsFeed(userId);
					media.printPost(posts);
					break;
				case "3":
					// To follow the friend based on the user ID
					System.out.println("Please Enter the userId of Friend to Follow");
					followerId = userInput.nextLine();
					media.follow(followerId, userId);
					break;
				case "4":
					// To unfollow the friend
					System.out.println("Please Enter the userId of Friend to UnFollow");
					followerId = userInput.nextLine();
					media.unfollow(followerId, userId);
					break;
				default:
					break outer;
				}
			}
		} catch (Exception e) {
			System.out.println("Exception Occurred" + e.getMessage());
		}
	}
}
