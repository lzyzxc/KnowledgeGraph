package com.tongji.knowledgegraph.model;



public class MyRelation extends BaseObject {

	/**
	 * 关系的ID  ==  聚合、连接、属于、包括等，这些关系可能是枚举字典，因此记录关系ID是有必要的
	 */
	private Long relationID;
	
	/**
	 * 关系名称
	 */
	private String name;
	
	/**
	 * 关系指向哪一个节点 == 可能这个节点还有关系【节点关系递增下去】
	 */
	private MyNode MyNode;
	
	public Long getRelationID() {
		return relationID;
	}

	public void setRelationID(Long relationID) {
		this.relationID = relationID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyNode getMyNode() {
		return MyNode;
	}

	public void setMyNode(MyNode myNode) {
		this.MyNode = myNode;
	}
	
}
