package com.tongji.knowledgegraph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MT
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class EdgeResponse {
    String from;
    String to;
    String name;
}
