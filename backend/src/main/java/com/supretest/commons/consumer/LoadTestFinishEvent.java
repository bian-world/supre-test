package com.supretest.commons.consumer;

import com.supretest.base.domain.LoadTestReport;
import org.springframework.scheduling.annotation.Async;

public interface LoadTestFinishEvent {
    @Async
    void execute(LoadTestReport loadTestReport);
}
