package com.supretest.dto;

import com.supretest.base.domain.TestCaseNode;
import lombok.Data;

@Data
public class NodeNumDTO extends TestCaseNode {
    private Integer caseNum;
}
