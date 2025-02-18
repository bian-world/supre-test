package com.supretest.track.service;

import com.supretest.base.domain.*;
import com.supretest.base.mapper.ProjectMapper;
import com.supretest.commons.utils.SessionUtils;
import com.supretest.track.request.testreview.TestReviewRelevanceRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestReviewProjectService {

    @Resource
    private ProjectMapper projectMapper;

    public List<String> getProjectIdsByReviewId() {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andWorkspaceIdEqualTo(SessionUtils.getCurrentWorkspaceId());
        List<Project> projects = projectMapper.selectByExample(example);
        return projects.stream().map(Project::getId).collect(Collectors.toList());
    }

    public List<Project> getProject(TestReviewRelevanceRequest request) {
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andIdIn(request.getProjectIds());
        if (StringUtils.isNotBlank(request.getName())) {
            criteria.andNameLike(StringUtils.wrapIfMissing(request.getName(), "%"));
        }
        return projectMapper.selectByExample(projectExample);
    }
}
