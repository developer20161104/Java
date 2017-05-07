package dijkstra;
import java.io.*;

public class Scan {
	
	public static int[][] getArray(String fname,int len)
	{
		int[][] arr= new int[len][len];
		try {
			BufferedReader in=new BufferedReader(new FileReader(fname));
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
	
	private static int Trans(String x)
	{
		if(x == null || x.length() == 0) return 10;
		if(x.charAt(0) == '-')
			return -1;
		else return (int)(x.charAt(0)-'0');
	}
	public static void main(String[] args)
	{
		
		int len=18;
		int[][] arr=Scan.getArray("datas.txt", len);
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<len;j++)
				System.out.print(arr[i][j]);
			System.out.println();
		}
	
	
	}
	
}
