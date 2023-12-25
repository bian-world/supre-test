package com.supretest.dto;

import lombok.Getter;
import lombok.Setter;
import net.sf.saxon.style.DataElement;

import java.util.Date;

@Getter
@Setter
public class BugCountBaseDTO {
    private String assignedTo;
    private Date assignedDate;
    private String project;
    private String branch;
    private int activatedCount;
    private int feedback;
    private String id;
    private String status;
    private String title;
    private Date closedDate;
    private Date resolvedDate;
    private String execution;
    private String product;
    private int severity;
}
