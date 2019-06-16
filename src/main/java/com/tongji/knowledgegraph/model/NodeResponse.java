package com.tongji.knowledgegraph.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.compiler.SrcTargetKey;

import java.util.List;
import java.util.Map;

/**
 * @author MT
 * @version 0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeResponse {
    String id;
    String label;

    int category;

    Map<String, Object> attributes;

    public NodeResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
