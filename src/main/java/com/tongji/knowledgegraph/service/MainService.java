package com.tongji.knowledgegraph.service;

import com.tongji.knowledgegraph.model.*;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Entity;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.neo4j.ogm.model.Edge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    private Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
    private Session session = driver.session();

    public ResponseEntity<?> Query(String query){
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

        // MT
        GraphResponseEntity graphResponseEntity = new GraphResponseEntity(new ArrayList<>(), new ArrayList<>());
        myNodes.forEach(eachNode -> {
//            System.out.println("id | " + eachNode.id());
//            System.out.println("keys |" + eachNode.asMap().keySet());
//            System.out.println("Label 1 | " + label);
            var nodeAttrs = eachNode.asMap();
            var allLabels = new ArrayList<String>();
            var cAttrs = new HashMap<String, Object>();
            cAttrs.put("类型", allLabels);
            nodeAttrs.keySet().forEach(key -> cAttrs.put(key, nodeAttrs.get(key)));
            eachNode.labels().forEach(allLabels::add);
            NodeResponse nodeResponse = new NodeResponse(cAttrs);
            nodeResponse.setId(Long.toString(eachNode.id()));
            if (nodeAttrs.containsKey("ns6__organization-name")) {
                nodeResponse.setLabel(nodeAttrs.get("ns6__organization-name").toString());
            } else {
                nodeResponse.setLabel(Long.toString(eachNode.id()));
            }
            String cateStr = "4";
            if (allLabels.size() > 1)
                 cateStr = allLabels.get(1).split("_")[0].substring(2, 3);
            nodeResponse.setCategory(Integer.parseInt(cateStr));
            graphResponseEntity.getNodes().add(nodeResponse);
        });
        myRelations.forEach(eachRelation -> {
            EdgeResponse edgeResponse = new EdgeResponse(Long.toString(eachRelation.startNodeId()),
                    Long.toString(eachRelation.endNodeId()), eachRelation.type(), eachRelation.asMap());
            graphResponseEntity.getEdges().add(edgeResponse);
        });

        System.out.println(result.toString());
        return new ResponseEntity<>(graphResponseEntity, HttpStatus.OK);
    }
    public ResponseEntity<?> matchOrg(String name){
        String query = "MATCH p=((:ns4__Organization{`ns6__organization-name`:'"+name+"'}) -[]-()) RETURN p";
        return Query(query);
    }
    public ResponseEntity<?> multiHop(String org1, String org2, Integer step){
        String query = "MATCH p=((:ns4__Organization{`ns6__organization-name`:'"+org1+"'}) -[*.."+step+"]-(:ns4__Organization{`ns6__organization-name`:'"+org2+"'})) RETURN p limit 1";
        return Query(query);
    }
    public ResponseEntity<?> around(String label, Integer id){
        String query = "MATCH p=((n:"+label+")-[]-()) where id(n)="+id+" RETURN p";
        return Query(query);
    }
}
