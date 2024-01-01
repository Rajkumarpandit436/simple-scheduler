package simplescheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleJob {

    @Autowired
    private Scheduler scheduler;
    public void scheduleJob(String jobName, String groupName, String cronExp, String desc, String triggerName, JobDataMap dataMap) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .storeDurably()
                .withIdentity(jobName)
                .usingJobData(dataMap)
                .withDescription(desc)
                .build();

        CronTrigger triggerBuild = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(triggerName)
                .withDescription(desc)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                .build();

        scheduler.scheduleJob(jobDetail, triggerBuild);
    }
}
