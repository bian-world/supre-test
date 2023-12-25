package com.supretest.track.request.testcase;

import com.supretest.base.domain.ModuleNode;
import com.supretest.track.dto.TestCaseNodeDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DragNodeRequest extends ModuleNode {

    List<String> nodeIds;
    TestCaseNodeDTO nodeTree;
}
