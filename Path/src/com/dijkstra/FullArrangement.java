package com.dijkstra;

import java.util.Arrays;

public class FullArrangement {
	private static final int NAN = 1000;
	
	//���ض����ַ���x��ȫ���ж�Ӧ���±�ֵ
	public static int[][] getFullArrangement(String x)
	{
		char[] a =x.toCharArray();
		Arrays.sort(a);
		int num =FullArrangement.num(a.length);
		char[][] pre =FullArrangement.total(a, num);
		int[][] res =new int[num][a.length];
		CenterNode[] node=CenterNode.values();
		for(int i=0;i<num;i++)
		{
			for(int j=0; j<a.length ;j++)
			{
				for(int k=0;k<node.length;k++)
				{
					if(pre[i][j] == node[k].getNode())
						res[i][j] = node[k].getIndex();
				}
			}
		}
		
		return res;
	}
	
	//��n��
	private static int num(int len)
	{
		if(len == 1 || len ==0) return 1;
		else {
			return len*num(len-1);
		}
	}
	
	//�ֵ��������㷨
	public static char[] nextChar(char[] x) 
	{
		int j=NAN;
		
		for(int i=x.length-2;i>=0;i--)
		{
			if( (x[i]-x[i+1])<0 ) 
			{
				j = i;
				break;
			}
		}
		
		if( j==NAN ) return x;
		
		int k=x.length-1;
		int temp =NAN;
		for(int i=k;i>=j;i--)
		{
			if((x[i]-x[j])>0 && (x[i]-x[j])<temp)
			{
				k = i;
				temp =x[i]-x[j];
			}
		}
		
		//��x[j]�� x[k]����
		char tem = x[j];
		x[j] = x[k];
		x[k] = tem;
		
		//���鵹�� 
		char[] nx=new char[x.length];
		int t=nx.length-1;
		for(int i=0;i<nx.length;i++)
		{  
			if(i<= j) nx[i]= x[i];
			else {
				nx[i] = x[t];
				t--;
			}
		}
		return nx;
	}
	
	//�������е�����
	public static char[][] total(char[] total,int num)
	{
		char[][] member = new char[num][total.length];
		for(int t=0;t<total.length;t++)
			member[0][t] =total[t];
		char[] next=FullArrangement.nextChar(total);
		int i=0;
		while(compare(member[i],next))
		{
			i++;
			for(int j=0;j<next.length;j++)
				member[i][j] = next[j];
			next=FullArrangement.nextChar(next);
		}
		return member;
	}
	
	//�ж�
	private static boolean compare(char[] a,char[] b)
	{
		int t=0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i] == b[i]) t++;
		}
		if(t==a.length) return false;
		else return true;
	}

	//����
	public static void UpDown(char[] x)
	{
		System.out.println(x);
		
		for(int i=x.length-1;i>0;i--)
		{
			char temp =x[i-1];
			x[i-1] = x[i];
			x[i] = temp;
			System.out.println(x);
		} 	
	}
}
