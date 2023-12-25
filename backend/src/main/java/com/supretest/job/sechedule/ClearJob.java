package com.supretest.job.sechedule;

import com.supretest.commons.utils.FileUtils;
import com.supretest.xmind.utils.FileUtil;
import org.quartz.JobExecutionContext;

import java.io.File;

public class ClearJob extends MsScheduleJob {

    @Override
    void businessExecute(JobExecutionContext context) {
        // 清理调试产生的body文件
        FileUtil.deleteDir(new File(FileUtils.BODY_FILE_DIR + "/tmp"));
    }
}
