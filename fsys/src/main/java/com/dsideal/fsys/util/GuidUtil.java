package com.dsideal.fsys.util;

import java.util.UUID;

/**
 * 
 * @author feilm220
 *
 */
public class GuidUtil {

	public static String getUuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toLowerCase();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++)
		System.out.println(getUuid());
	}
}
