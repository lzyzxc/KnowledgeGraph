package com.tongji.knowledgegraph.model;


public class Node extends BaseObject {
    
	/**
	 * 节点的uuid == 对应其他数据库中的主键
	 */
	private Long uuid;
	
	/**
	 * 节点里面是否包含有边 == 关系
	 */
	private Edge edge;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}
}
