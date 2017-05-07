package dijkstra;
import java.util.*;

public class Easilist {
	private static final int INF = 1000;
	
	public static int[] dijkstra(int[][] mat,int s,int e,char[] elem)
	{
		boolean[] flag = new boolean[mat.length];
		int[] dist = new int [mat.length];
		int[] prej =new int[mat.length];
		int s1 = s;
		
		Arrays.fill(flag, false);
		
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat.length;j++)
				if(mat[i][j] < 0)  mat[i][j] = INF;
		
		flag[s] =true;
		while(flag[e] != true)
		{
			for(int j=0;j< mat.length;j++)
			{
				dist[j] = mat[s][j];
			}			
			int p = 0;
			for(int k=0;k<mat.length;k++)
			{
				int min = INF;
				for(int l=0 ;l<mat.length ;l++)//find the min
				{
					if(flag[l] != true && dist[l] < min)
					{
						min = dist[l];
						p = l ;
					}
				}
				
				flag[p] = true;
				
				for(int y=0;y<mat.length ;y++)//refresh dist
				{
					int tmp = (mat[p][y]== INF ? INF : (min+mat[p][y]));
					if(flag[y] != true && dist[y] > dist[p]+mat[p][y])
					{
						dist[y] = tmp;
						prej[y] = p;
					}
				}
				s = p;
				//if(s == e) break;
			}
		}
		
		System.out.printf("dijkstra(%c): \n", elem[s1]);
	    for(int i=0;i<elem.length;i++)
	    System.out.printf("  shortest(%c, %c)=%d\n", elem[s1], elem[i], dist[i]);
	    
	    //for(int j =0; j<elem.length;j++)
	    	System.out.println(prej[0]);
	    
	    return dist;
	}
	
	public static void all(int[][] mat)
	{

		char[] elem = new char[mat[1].length];
		for(int i =0;i<mat[1].length;i++)
			elem[i] =(char) ( 65 + i);
		int[][] all=new int[elem.length][elem.length];
		for(int i=0;i<elem.length;i++)
		{
			all[i] =Easilist.dijkstra(mat,i,elem.length-1,elem); 
		}
		all[elem.length-1] = Easilist.dijkstra(mat,elem.length-1,0,elem);
		
		for(int i=0;i<elem.length;i++)
		{
			for(int j=0;j<elem.length;j++)
				System.out.printf("%5d",all[i][j]);
			System.out.println();
		}
	}
	
	
	public static void main(String[] args)
	{
		//Scanner in = new Scanner(new File)
		long t1 = System.currentTimeMillis();
		int[][] mat ={
			{0,6,3,-1,-1,-1,-1},
			{6,0,2,5,-1,-1,1},
			{3,2,0,3,4,-1,-1},
			{-1,5,3,0,2,3,2},
			{-1,-1,4,2,0,5,-1},
			{-1,-1,-1,3,5,0,3},
			{-1,1,-1,2,-1,3,0}
		};
		char[] elem = new char[mat[1].length];
		for(int i =0;i<mat[1].length;i++)
			elem[i] =(char) (65 + i);
		
		Easilist.all(mat);
		long t2 = System.currentTimeMillis();
		System.out.println("ÓÃÊ±" + (t2-t1) + "ms");
	}
}
