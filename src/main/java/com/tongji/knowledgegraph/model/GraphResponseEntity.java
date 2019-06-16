package com.tongji.knowledgegraph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author MT
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class GraphResponseEntity {
    List<NodeResponse> nodes;
    List<EdgeResponse> edges;
}
