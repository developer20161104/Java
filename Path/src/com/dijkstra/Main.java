package com.dijkstra;

public class Main {
	static int[][]  mat;//距离矩阵
	static CenterNode[] node;//中间节点集
	static String[] elem={"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","E"};//所有结点名
	
	public static void main(String[] args) {

		int len =18;
		
		//读取文件获得距离矩阵
		mat =Scan.getArray("data.txt", len);
		
		//对中间结点进行全排列
		int[][] FullCenPath =FullArrangement.getFullArrangement("abcdef");
		
		node=CenterNode.values();
		
		//首部到中间起点的对象
		Path_Distance_class[] first =new Path_Distance_class[node.length];
		for(int i=0;i<node.length;i++)
			first[i] =Ph_Dt_dijktra.dijkstra(0, node[i].getIndex());
		
		//尾部到中间终点的对象
		Path_Distance_class[] third =new Path_Distance_class[node.length];
		for(int i=0;i<node.length;i++)
			third[i] = Ph_Dt_dijktra.dijkstra(elem.length-1, node[i].getIndex());
		
		//剔除不必要的结点，简化计算
		CenterNode[] start = Process.judge(0, first);
		CenterNode[] end = Process.judge(elem.length-1,third);
		
		//得到剔除后的首尾两段对象
		Path_Distance_class[] nfir = Process.Refresh(start, first);
		Path_Distance_class[] ntrd = Process.Refresh(end, third);
		
		//中间对象
		Path_Distance_class[] sec = Process.getCen(start, end, FullCenPath);
		
		//总体对象集合
		Path_Distance_class[] total = Process.getResult(nfir, sec, ntrd);
		
		//对总体进行筛选并打印相应的结果
		Process.Display(total);
	}
}
