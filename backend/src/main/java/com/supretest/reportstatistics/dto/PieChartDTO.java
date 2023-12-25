package com.supretest.reportstatistics.dto;

import com.alibaba.fastjson.JSONObject;
import com.supretest.reportstatistics.dto.charts.Series;
import com.supretest.reportstatistics.dto.charts.Title;
import com.supretest.reportstatistics.dto.charts.XAxis;
import com.supretest.reportstatistics.dto.charts.YAxis;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PieChartDTO {
    private JSONObject dataset;
    private JSONObject tooltip;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Series> series;
    private List<Title> title;
    private int width;

    public PieChartDTO() {
        tooltip = new JSONObject();
    }
}
