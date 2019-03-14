package com.scp.password;

import java.util.Random;

public class StringRandomPassword {

	public static void main(String[] args) {
		String character = "abcdefghijklmnopqrstuvwxyz";
		String randomString = "";
		int length = 5;
		Random rand = new Random();
		char[] text = new char[length];
		for(int i=0;i<length;i++){
			text[i] = character.charAt(rand.nextInt(character.length()));
		}
		for(int i=0;i<text.length;i++){
			randomString+=text[i];
		}
		System.out.println(randomString);
	}

}
