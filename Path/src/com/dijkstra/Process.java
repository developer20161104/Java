package com.dijkstra;

import java.util.ArrayList;

public class Process {
	//剔除不必要的结点
		public static CenterNode[] judge(int pos,Path_Distance_class[] a)
		{
			ArrayList<CenterNode> arr= new ArrayList<CenterNode>();
			
			for(int i=0;i<Main.mat[pos].length;i++)
			{
				if(Process.contains(i,pos,a))	
					for(int q=0;q<Main.node.length;q++)
						if(i == Main.node[q].getIndex())
							arr.add(Main.node[q]);
			}
			CenterNode[] ar =new CenterNode[arr.size()];
			for(int i=0;i<arr.size();i++)
			{
				ar[i] =arr.get(i);
			}
			return ar;
		}
		
		//判断的标准
		private static  boolean contains(int i,int pos,Path_Distance_class[] a)
		{
			if(Process.OutNodes(i, a))
				for(int j=0;j<Main.node.length;j++)
				{
						if((i == Main.node[j].getIndex()) && (a[j].getDistance() <=Process.getMean()) )
							return true; 
				}
				return false;
		}
		
		
		//标准1：将以中间结点为终点的过程路径中出现的中间结点剔除
		private static boolean OutNodes(int q,Path_Distance_class[] a)
		{
			ArrayList<Integer> arr =new ArrayList<Integer>();
			for(int i=0;i<a.length ;i++)
			{
				if(a[i].equals())
					arr.add(a[i].getEnd());
			}
			if(arr.size() !=0)
			{
				for(int w=0;w<arr.size();w++)
				{
					if(q == arr.get(w))
						return false;
				}
			}  
			return true;
		}
		
		//标准2；取路径长度不大于所有可通过路径长度的平均向上取整值
			private static int getMean()
			{
				double sum = 0;
				int total = 0;
				for(int i=0;i<Main.mat[0].length;i++)
					for(int j=0;j<Main.mat.length;j++)
					{
						if(Main.mat[i][j] != 0 && Main.mat[i][j] != Ph_Dt_dijktra.INF)
						{
							sum += Main.mat[i][j];
							total++;
						}
					}
				return (new Double(Math.ceil(sum/total))).intValue();
			}
		//剔除不必要的对象
		public static Path_Distance_class[] Refresh(CenterNode[] x,Path_Distance_class[] to)
		{
			int k=0;
			Path_Distance_class[] save=new Path_Distance_class[x.length];
			for(int i=0;i<to.length;i++)
				for(int j=0;j<x.length;j++)
					if(to[i].getEnd() == x[j].getIndex())
					{
						save[k]= new Path_Distance_class();
						save[k]= to[i];
						k++;
					}
			return save;
		}
		
		//求解中间结点序列集合的距离与路径
		public static Path_Distance_class[] getCen(CenterNode[] start,CenterNode[] end,int[][] full)
		{
			int[][] ncen =new int[full.length][full[0].length];
			
			int k=0; int len=0;
			for(int i=0;i<full.length;i++)
				for(int j=0;j<start.length;j++)
					if((full[i][0] == start[j].getIndex()))
						for(int l=0;l<end.length;l++)
							if((full[i][full[i].length-1]) == end[l].getIndex())
							{
								for(int g=0;g<full[0].length;g++)
									ncen[k][g] = full[i][g];
								k++;
							}
			for(int i=0;(ncen[i][0] != '\0');i++) len++; 
			Path_Distance_class[] cen_class = new Path_Distance_class[len];
			for(int i=0 ;i<len; i++)
				cen_class[i] = Process.cenPath_Dit(ncen[i], start, end);
			
			return cen_class;
		}
		
		//通过给出的一个中间结点序列得到中间过程的距离与路径
		private static Path_Distance_class cenPath_Dit(int[] path,CenterNode[] start,CenterNode[] end)
		{
			
			Path_Distance_class[] cen = new Path_Distance_class[path.length-1];
			for(int i=0; i<path.length-1 ;i++)
			{
				cen[i] =new Path_Distance_class();
				cen[i] =Ph_Dt_dijktra.dijkstra(path[i],path[i+1]);
			}
			Path_Distance_class res = new Path_Distance_class();
			
			res.setStart(path[0]);
			res.setEnd(path[path.length-1]);
			
			int sum=0;
			ArrayList<String> tot= new ArrayList<String>();
			for(int i=0;i<cen.length-1;i++)
			{
				sum+=cen[i].getDistance();
				String[] str=cen[i].getPath();
				for(int j=0;j<str.length-1;j++)
					tot.add(str[j]);
			}
			String[] str=cen[cen.length-1].getPath();
			sum+= cen[cen.length-1].getDistance();
			res.setDistance(sum);
			for(int j=0;j<str.length;j++)
				tot.add(str[j]);
			String[] npath=new String[tot.size()];
			for(int p=0;p<npath.length;p++)
				npath[p] = tot.get(p);
			
			res.setPath(npath);
		
			return res;
		}
		
		//将三个分对象集整合成一个总对象集
		public static Path_Distance_class[] getResult(Path_Distance_class[] fir,Path_Distance_class[] sec,Path_Distance_class[] thr)
		{
			ArrayList<Path_Distance_class> total = new ArrayList<Path_Distance_class>();
			
			for(int i=0;i<fir.length ;i++)
			{
				for(int j=0;j<sec.length ;j++)
				{
					if(fir[i].getEnd() == sec[j].getStart())
					{
						for(int k=0;k<thr.length;k++)
						{
							if(sec[j].getEnd() == thr[k].getEnd())
								total.add(Process.getElem(fir[i], sec[j], thr[k]));
						}
					}
				}
			}
			
			Path_Distance_class[] Total =new Path_Distance_class[total.size()];
			for(int i=0;i<total.size();i++)
				Total[i] = total.get(i);
			
			return Total;
		}
		
		//总对象的匹配函数
		private static Path_Distance_class getElem(Path_Distance_class fir,Path_Distance_class sec,Path_Distance_class thr)
		{
			ArrayList<String> tot= new ArrayList<String>();
			String[] str0 = fir.getPath();
			String[] str1 = sec.getPath();
			String[] str2 = thr.getPath();
			for(int j=0;j<str0.length-1;j++)
				tot.add(str0[j]);
			for(int j=0;j<str1.length-1;j++)
				tot.add(str1[j]);
			for(int j=str2.length-1;j>=0;j--)
				tot.add(str2[j]);
			String[] npath=new String[tot.size()];
			for(int p=0;p<npath.length;p++)
				npath[p] = tot.get(p);
			return new Path_Distance_class(fir.getDistance()+sec.getDistance()+thr.getDistance(),npath,fir.getStart(),thr.getStart());
		}
		
		//打印函数
		public static void Display(Path_Distance_class[] total)
		{
			int k=0;
			int len=total[0].getPath().length;
			Path_Distance_class min= total[0];
			Path_Distance_class min2 = total[0];
			for(int i=1 ;i<total.length ;i++)
			{ 
				if(total[i].getDistance() < min.getDistance()) min = total[i];
				if(total[i].getPath().length < min.getPath().length) min2 =total[i];
			}
			ArrayList<Path_Distance_class> arr = new ArrayList<Path_Distance_class>();
			ArrayList<Path_Distance_class> arr2 = new ArrayList<Path_Distance_class>();
			arr.add(min);
			arr2.add(min2);
			int len2= min2.getPath().length;
			for(int i=0;i<total.length ;i++)
			{
				if(min.getDistance() == total[i].getDistance()) 
					if(Process.RmSame(min, total, len, i)) arr.add(total[i]);
				if(total[i].getPath().length<=9)
				{
					k = i;
				}
				if(len2 == total[i].getPath().length)
					if(Process.RmSame(min2, total, len2, i)) arr2.add(total[i]);
			}
			
			if(k == 0)
			{
				System.out.println("无最优路径");
				System.out.println();
				System.out.println("距离最优路径如下：");
				System.out.println("相对最短距离是：" + arr.get(0).getDistance());
				System.out.println("路径如下：");
				for(int i=0;i<arr.size();i++)
				{
					for(int j=0;j<arr.get(i).getPath().length-1;j++)
						System.out.printf("%s ->",arr.get(i).getPath()[j]);
					System.out.printf("%s\n",arr.get(i).getPath()[len-1]);
				}
				System.out.println("步数为：" + len);
				
				System.out.println();
				System.out.println("路径长度最优路径如下：");
				for(int i=0;i<arr2.size();i++)
				{
					System.out.println("距离是：" + arr2.get(i).getDistance() );
					System.out.println("相对最短路径如下：");
					for(int j=0;j<arr2.get(i).getPath().length-1;j++)
						System.out.printf("%s ->",arr2.get(i).getPath()[j]);
					System.out.printf("%s\n",arr2.get(i).getPath()[len2-1]);
				}
				System.out.println("相对最短步数为：" + len2);
			}
			else 
			{
				System.out.println("有最优路径");
				System.out.println();
				System.out.println("最短距离是" + total[k].getDistance());
				for(int i=0;i<total[k].getPath().length-1;i++)
					System.out.printf("%s ->",total[k].getPath()[i]);
				System.out.printf("%s",total[k].getPath()[len-1]);
			}
		}
		
		private static boolean RmSame(Path_Distance_class min,Path_Distance_class[] total,int len,int i)
		{
			for(int p=0;p<len;p++)
			{
				if((min.getPath()[p].compareTo(total[i].getPath()[p])) != 0)
					return true;
			}
			return false;
		}
}
