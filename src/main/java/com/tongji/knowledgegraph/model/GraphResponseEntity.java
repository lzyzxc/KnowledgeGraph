package com.tongji.knowledgegraph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author MT
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class GraphResponseEntity implements Serializable {
    List<NodeResponse> nodes;
    List<EdgeResponse> edges;

    public GraphResponseEntity() {
    }
}
