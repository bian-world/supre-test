package com.supretest.track.controller;


import com.supretest.base.domain.TestCase;
import com.supretest.i18n.Translator;
import com.supretest.performance.base.ChartsData;
import com.supretest.track.response.BugStatustics;
import com.supretest.track.response.TrackCountResult;
import com.supretest.track.response.TrackStatisticsDTO;
import com.supretest.track.service.TestCaseService;
import com.supretest.track.service.TrackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Resource
    private TrackService trackService;
    @Resource
    private TestCaseService testCaseService;

    @GetMapping("/count/{projectId}")
    public TrackStatisticsDTO getTrackCount(@PathVariable String projectId) {
        return this.getTrackCount(projectId, null);
    }
    @GetMapping("/count/{projectId}/{subProjectId}")
    public TrackStatisticsDTO getTrackCount(@PathVariable String projectId, @PathVariable String subProjectId) {
        TrackStatisticsDTO statistics = new TrackStatisticsDTO();
        List<TrackCountResult> priorityResults = trackService.countPriority(projectId, subProjectId);
        statistics.countPriority(priorityResults);

        long size = trackService.countCreatedThisWeek(projectId, subProjectId);
        statistics.setThisWeekAddedCount(size);

        List<TrackCountResult> statusResults = trackService.countStatus(projectId, subProjectId);
        statistics.countStatus(statusResults);

        long total = statistics.getPrepareCount() + statistics.getPassCount() + statistics.getUnPassCount();
        if (total != 0) {
            float reviewed = (float) (statistics.getPassCount() + statistics.getUnPassCount()) * 100 / total;
            DecimalFormat df = new DecimalFormat("0.0");
            statistics.setReviewRage(df.format(reviewed) + "%");
        }

        statistics.setP0CountStr("P0&nbsp;&nbsp;<br/><br/>" + statistics.getP0CaseCountNumber());
        statistics.setP1CountStr("P1&nbsp;&nbsp;<br/><br/>" + statistics.getP1CaseCountNumber());
        statistics.setP2CountStr("P2&nbsp;&nbsp;<br/><br/>" + statistics.getP2CaseCountNumber());
        statistics.setP3CountStr("P3&nbsp;&nbsp;<br/><br/>" + statistics.getP3CaseCountNumber());
        return statistics;
    }

    @GetMapping("/relevance/count/{projectId}")
    public TrackStatisticsDTO getRelevanceCount(@PathVariable String projectId) {
        return this.getRelevanceCount(projectId, null);
    }

    @GetMapping("/relevance/count/{projectId}/{subProjectId}")
    public TrackStatisticsDTO getRelevanceCount(@PathVariable String projectId, @PathVariable String subProjectId) {
        TrackStatisticsDTO statistics = new TrackStatisticsDTO();

        List<TrackCountResult> relevanceResults = trackService.countRelevance(projectId, subProjectId);
        statistics.countRelevance(relevanceResults);

        long size = trackService.countRelevanceCreatedThisWeek(projectId, subProjectId);
        statistics.setThisWeekAddedCount(size);

        List<TestCase> list = testCaseService.getTestCaseByProjectId(projectId);
        long total = list.size();
        int coverage = trackService.countCoverage(projectId, subProjectId);
        statistics.setCoverageCount(coverage);
        statistics.setUncoverageCount(total - coverage);

        if (total != 0) {
            float coverageRageNumber = (float) statistics.getCoverageCount() * 100 / total;
            DecimalFormat df = new DecimalFormat("0.0");
            statistics.setCoverageRage(df.format(coverageRageNumber) + "%");
        }

        statistics.setApiCaseCountStr(Translator.get("api_case") + "&nbsp;&nbsp;<br/><br/>" + statistics.getApiCaseCount());
        statistics.setPerformanceCaseCountStr(Translator.get("performance_case") + "&nbsp;&nbsp;<br/><br/>" + statistics.getPerformanceCaseCount());
        statistics.setScenarioCaseStr(Translator.get("scenario_case") + "&nbsp;&nbsp;<br/><br/>" + statistics.getScenarioCaseCount());

        return statistics;
    }

    @GetMapping("/case/bar/{projectId}")
    public List<ChartsData> getCaseMaintenanceBar(@PathVariable String projectId) {
        return this.getCaseMaintenanceBar(projectId, null);
    }

    @GetMapping("/case/bar/{projectId}/{subProjectId}")
    public List<ChartsData> getCaseMaintenanceBar(@PathVariable String projectId, @PathVariable String subProjectId) {
        return trackService.getCaseMaintenanceBar(projectId, subProjectId);
    }

    @GetMapping("/bug/count/{projectId}")
    public BugStatustics getBugStatistics(@PathVariable String projectId) {
        return trackService.getBugStatistics(projectId);
    }
}
