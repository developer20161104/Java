package com.dijkstra;

//包含起始点，长度与相关路径的对象
public class Path_Distance_class {
	private int start;//起点
	private int end;//终点
	private int distance;//距离
	private String[] Path;//路径
	
	public Path_Distance_class(int dis,String[] Pa,int start,int en)
	{
		this.start =start;
		this.end =en;
		this.distance = dis;
		this.Path = Pa;
	}
	
	public Path_Distance_class()
	{
		this.start =0;
		this.end =0;
		this.distance =0;
		this.Path =null;
	}
	public int getStart() {return this.start;}
	public int getEnd() {return this.end;}
	public int getDistance() {return this.distance;}
	public String[] getPath() {return this.Path;}
	
	public void setStart(int st) { this.start =st;}
	public void setEnd(int ed) {this.end =ed;}
	public void setDistance(int dis) {this.distance = dis;}
	public void setPath(String[] pa) {this.Path =pa;}
	
	public boolean equals()
	{
		String[] path=this.Path;
		for(int j=1;j<path.length-1;j++)
		{
			for(int k=0;k<Main.node.length;k++)
			{
				if(path[j] == Main.node[k].toString())
					return true;
			}
		}
		return false;
	}
}
