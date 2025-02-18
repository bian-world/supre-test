package com.supretest.track.dto;

import com.supretest.base.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO extends Schedule {
    /**
     * 定时任务下一次执行时间
     */
    private Long scheduleExecuteTime;
}
