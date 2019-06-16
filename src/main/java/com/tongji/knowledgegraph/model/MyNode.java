package com.tongji.knowledgegraph.model;


public class MyNode extends BaseObject {
    
	/**
	 * 节点的uuid == 对应其他数据库中的主键
	 */
	private Long uuid;
	
	/**
	 * 节点里面是否包含有边 == 关系
	 */
	private MyRelation myRelation;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public MyRelation getMyRelation() {
		return myRelation;
	}

	public void setMyRelation(MyRelation myRelation) {
		this.myRelation = myRelation;
	}
}
