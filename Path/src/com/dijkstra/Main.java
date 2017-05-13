package com.dijkstra;

public class Main {
	static int[][]  mat;//�������
	static CenterNode[] node;//�м�ڵ㼯
	static String[] elem={"S","N1","N2","N3","N4","N5","N6","N7","N8","N9","N10","N11","N12","N13","N14","N15","N16","E"};//���н����
	
	public static void main(String[] args) {

		int len =18;
		
		//��ȡ�ļ���þ������
		mat =Scan.getArray("data.txt", len);
		
		//���м������ȫ����
		int[][] FullCenPath =FullArrangement.getFullArrangement("abcdef");
		
		node=CenterNode.values();
		
		//�ײ����м����Ķ���
		Path_Distance_class[] first =new Path_Distance_class[node.length];
		for(int i=0;i<node.length;i++)
			first[i] =Ph_Dt_dijktra.dijkstra(0, node[i].getIndex());
		
		//β�����м��յ�Ķ���
		Path_Distance_class[] third =new Path_Distance_class[node.length];
		for(int i=0;i<node.length;i++)
			third[i] = Ph_Dt_dijktra.dijkstra(elem.length-1, node[i].getIndex());
		
		//�޳�����Ҫ�Ľ�㣬�򻯼���
		CenterNode[] start = Process.judge(0, first);
		CenterNode[] end = Process.judge(elem.length-1,third);
		
		//�õ��޳������β���ζ���
		Path_Distance_class[] nfir = Process.Refresh(start, first);
		Path_Distance_class[] ntrd = Process.Refresh(end, third);
		
		//�м����
		Path_Distance_class[] sec = Process.getCen(start, end, FullCenPath);
		
		//������󼯺�
		Path_Distance_class[] total = Process.getResult(nfir, sec, ntrd);
		
		//���������ɸѡ����ӡ��Ӧ�Ľ��
		Process.Display(total);
	}
}
