package com.newshop;

import java.util.Scanner;

public class test{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int mang[] = new int[n];
		for (int i = 0; i < n; i++) {
			mang[i] = sc.nextInt();
		}
		int dem =0;
		String in ="";
		for (int i = 0 ,j =1; j<n; i++ , j++) {
			if(mang[i]<mang[j]) {
				dem++;
			}else if(mang[i]>mang[j]){
				dem--;
			}
		}
		if(dem == n-1 || dem == 1-n){
			in ="YES";
		}else {
			in = "NO";
		}
		System.out.println(in);
	}
}