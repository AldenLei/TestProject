package com.qf.administrator.baozou.entity;


import com.qf.administrator.baozou.R;

public class PrortaitUtils {
	private static int[] protraits=new int[]{R.drawable.ic_logo,R.drawable.avatar_m,R.drawable.portrait_2,R.drawable.portrait_3};
	
	public static int  conversionIdToRes(int id){
		if(isSystemProtraits(id)){
			return protraits[(int) id];
		}else{
			return protraits[0];
		}
	}
	
	public static boolean isSystemProtraits(int id){
		return id >= 0 && id < protraits.length;
	}
}
