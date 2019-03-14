package com.scp.password;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class PassWordGeneration {
	public static void main(String[] args) {
		System.out.println("inside main");
		
		for(int i=0;i<10;i++){
			String password = generateRandomPassword(PasswordStrength.HIGH);
			System.out.println(password+" : "+password.length());	
		}
		
	}

	private static String generateRandomPassword(PasswordStrength strength) {
		int passwordLen = ThreadLocalRandom.current().nextInt(6,12);
		int capsLen = 0,smallLen =0,numsLen =0;
		String capsLetter = "",smallLetter ="",numberLetters = "",spclChars="";
		
		switch(strength){
			case LOW:
				capsLen = ThreadLocalRandom.current().nextInt(1,passwordLen-1);
				smallLen = passwordLen-capsLen;
				capsLetter = generateRandomChars(capsLen).toUpperCase();
				smallLetter = generateRandomChars(smallLen);
				return shufflePassword(capsLetter,smallLetter);
			case MIDDLE:
				numsLen = passwordLen/3;
				capsLen = ThreadLocalRandom.current().nextInt(1,passwordLen-(numsLen+ThreadLocalRandom.current().nextInt(1, 3)));
				smallLen = (passwordLen-numsLen)-capsLen;
				capsLetter = generateRandomChars(capsLen).toUpperCase();
				smallLetter = generateRandomChars(smallLen);
				numberLetters= generateRandomNumberChars(numsLen);
				return shufflePassword(capsLetter,smallLetter,numberLetters);
			case HIGH:
				int spclCharIndex =0;
				numsLen = passwordLen/3;
				capsLen = ThreadLocalRandom.current().nextInt(1,passwordLen-(numsLen+ThreadLocalRandom.current().nextInt(1, 3)));
				smallLen = (passwordLen-numsLen)-capsLen;
				if(capsLen > smallLen || capsLen > numsLen){
						spclCharIndex = capsLen/2;
						capsLen=capsLen-spclCharIndex;
				}else if(smallLen > capsLen || smallLen > numsLen){
					 spclCharIndex = smallLen/2;
					smallLen=smallLen-spclCharIndex;
				}else{
					 spclCharIndex = numsLen/2;
					numsLen=numsLen-spclCharIndex;
				}
				
				numberLetters= generateRandomNumberChars(numsLen);
				spclChars= generateRandomSpclChars(spclCharIndex);
				capsLetter = generateRandomChars(capsLen).toUpperCase();
				smallLetter = generateRandomChars(smallLen);
				return shufflePassword(smallLetter,capsLetter,numberLetters,spclChars);
			default:
				System.out.println("Not matching criteria");
				return null;
		}
		
		
		
	}

	private static String shufflePassword(String ...passwords) {
		String tempPassword = "";
		String finalPassword = "";
		for(String str :passwords)
				tempPassword+=str;
				
			ArrayList<String> listOfStrs = new ArrayList();
			for (char item: tempPassword.toCharArray()) {
				listOfStrs.add(item+"");
			}
			Collections.shuffle(listOfStrs);
			for(String str : listOfStrs){
				finalPassword +=str;
			}
			return finalPassword;
		
	}

	private static String generateRandomNumberChars(int capsLen) {
		String chars = "";
		for(int i=0;i<capsLen;i++){
			chars+=(char)ThreadLocalRandom.current().nextInt(48,58);
		}
		return chars;
	}
	
	private static String generateRandomSpclChars(int capsLen) {
		String chars = "";
		for(int i=0;i<capsLen;i++){
			chars+=(char)ThreadLocalRandom.current().nextInt(33,48);
		}
		return chars;
	}
	
	private static String generateRandomChars(int capsLen) {
		String chars = "";
		for(int i=0;i<capsLen;i++){
			chars+=(char)ThreadLocalRandom.current().nextInt(97,123);
		}
		return chars;
	}
}


enum PasswordStrength{
	LOW,   //a - c+s
	MIDDLE,//a (c+s) + n
	HIGH //a (c+s) + n + spch
}


