package com.dijkstra;

//中间结点的枚举类
public enum CenterNode {
	N2('a',2),N4('b',4),N7('c',7),N12('d',12),N13('e',13),N14('f',14);
	
	private char node;
	private int index;
	private CenterNode(char node,int in)
	{
		this.node =node;
		this.index =in;
	}
	
	public char getNode()
	{
		return this.node;
	}
	
	public int getIndex()
	{
		return this.index;
	}
}
