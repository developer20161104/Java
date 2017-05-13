package com.dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
public class Scan {
	
	//文件读取函数
	public static int[][] getArray(String fname,int len)
	{
		int[][] arr= new int[len][len];
		try (BufferedReader in=new BufferedReader(new FileReader(fname))){
			String cen =new String();
			int j=0;
			while((cen=in.readLine())!=null && j<len)
			{
				
				String[] sp =cen.split(",");
				for(int i=0;i<sp.length;i++)
				{
					int t=Scan.Trans(sp[i]);
					if(t !=10)
						arr[j][i]=t;
				}
				j++;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return arr;
	}
	
	//char转化为int类型方便以后的计算
	private static int Trans(String x)
	{
		if(x == null || x.length() == 0) return 10;
		if(x.charAt(0) == '-')
			return -1;
		else return (int)(x.charAt(0)-'0');
	}
	
}
