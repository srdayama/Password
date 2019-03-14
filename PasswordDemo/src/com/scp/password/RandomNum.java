package com.scp.password;

public class RandomNum {

	public static void main(String[] args) {
		System.out.println(RandomNum.randomDigit());
}

	public static String randomDigit(){
		String otp="";
		for(int i=0;i<6;i++){
			 otp = otp+(int)(Math.random()*10);
		}
		return otp;
	}
}
