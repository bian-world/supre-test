package com.supretest.reportstatistics.dto.table;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestCaseCountTableDataDTO {
    List<TestCaseCountTableItemDataDTO> heads;
    List<TestCaseCountTableRowDTO> data;
}
