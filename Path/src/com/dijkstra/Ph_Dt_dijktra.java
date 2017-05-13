package com.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

public class Ph_Dt_dijktra {
	static final int INF =Integer.MAX_VALUE;//无穷远路径长度
	
	//返回包含起点、终点下标值，距离与路径的对象
	public static Path_Distance_class dijkstra(int s,int e)
	{
		boolean[] flag = new boolean[Main.mat.length];
		int[] dist = new int [Main.mat.length];
		int[] prej =new int[Main.mat.length];
		int s1 = s;
		
		Arrays.fill(flag, false);
		
		for(int i=0;i<Main.mat.length;i++)
			for(int j=0;j<Main.mat.length;j++)
			{
				if(Main.mat[i][j] < 0)  Main.mat[i][j] = INF;//将不能一步到达的路径置为INF
				if((i == 11 && j== 12) || (i == 12 && j == 11)) Main.mat[i][j] = INF;//将有危险的路径置为INF
			}
		
		flag[s] =true;
		
		while(flag[e] != true)
		{
			for(int j=0;j< Main.mat.length;j++)
			{
				dist[j] = Main.mat[s][j];
				if(dist[j]>=INF) prej[j] =-1;
				else prej[j] =s1;
			}			
			
			int p = 0;
			for(int k=0;k<Main.mat.length;k++)
			{
				int min = INF; 
				for(int l=0 ;l<Main.mat.length ;l++)
				{
					if(flag[l] != true && dist[l] < min)
					{
						min = dist[l];
						p = l ;
					}
				}
				
				flag[p] = true;
				
				for(int y=0;y<Main.mat.length ;y++)
				{
					if(flag[y] != true && Main.mat[p][y] <INF)
					{
						if(dist[y] > dist[p]+Main.mat[p][y])
						{
							dist[y] = dist[p]+Main.mat[p][y];
							prej[y] = p;
						}
					}
				}
			}
		}	    
	    ArrayList<String> ar=new ArrayList<String>();
	    	Ph_Dt_dijktra.getPath(prej, e,s1,ar);
	    
	    String[] Path =new String[ar.size()];
	    for(int i=0;i<Path.length;i++)
	    	Path[i]=ar.get(i);
	    return new Path_Distance_class(dist[e],Path,s1,e);
	}
	
	//递归求解路径
	public static void getPath(int[] prej,int x,int stop,ArrayList<String> arr)
	{
		if(prej[x]!=stop)  getPath(prej,prej[x],stop,arr);
		else arr.add(Main.elem[prej[x]]);
		arr.add(Main.elem[x]);
	}
}
