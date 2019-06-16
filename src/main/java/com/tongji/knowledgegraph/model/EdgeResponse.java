package com.tongji.knowledgegraph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author MT
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class EdgeResponse {
    String from;
    String to;
    String label;

    Map<String, Object> attributes;

    public EdgeResponse() {
    }

}
