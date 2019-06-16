package com.tongji.knowledgegraph.model;

import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {
    private ArrayList<Node> nodes;
    private ArrayList<Relationship> relationships;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }
    
}
