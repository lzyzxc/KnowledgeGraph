package com.tongji.knowledgegraph.service;

import com.tongji.knowledgegraph.model.MyNode;
import com.tongji.knowledgegraph.model.MyRelation;
import com.tongji.knowledgegraph.model.Result;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Entity;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    private Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
    private Session session = driver.session();

    public Result test(String name){
        String query = "MATCH p=((:ns4__Organization{`ns6__organization-name`:'"+name+"'}) -[]-()) RETURN p";
        StatementResult result = session.run(query);
        Result myResult = new Result();
        ArrayList<Node> myNodes = new ArrayList<>();
        ArrayList<Relationship> myRelations = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            Map<Long, Node> nodesMap = new HashMap<>();
            Path p = record.get("p").asPath();
            System.out.println(p.length());
            System.out.println("====================================");
            Iterable<Node> nodes = p.nodes();
            for (Node node : nodes) {
                if(!myNodes.contains(node)){
                    myNodes.add(node);
                }
                nodesMap.put(node.id(),node);
            }

            /**
             * 打印最短路径里面的关系 == 关系包括起始节点的ID和末尾节点的ID，以及关系的type类型
             */
            Iterable<Relationship> relationships = p.relationships();
            for (Relationship relationship : relationships) {
                myRelations.add(relationship);
                Long startID = relationship.startNodeId();
                Long endID = relationship.endNodeId();
                String rType = relationship.type();
                /**
                 * asMap 相当于 节点的properties属性信息
                 */
                System.out.println(
                        nodesMap.get(startID).asMap() + "-" + rType + "-"
                                + nodesMap.get(endID).asMap());
            }
        }
        myResult.setNodes(myNodes);
        myResult.setRelationships(myRelations);
        System.out.println(result.toString());
        return myResult;
    }
}
