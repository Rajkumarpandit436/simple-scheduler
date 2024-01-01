package simplescheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {
    private static int count = 1;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(count++ +" Executing job at " + System.currentTimeMillis() +" with job name: "+jobExecutionContext.getJobDetail().getKey().getName()+" with job description: "+jobExecutionContext.getJobDetail().getDescription());
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println(dataMap.get("name")+" "+dataMap.get("address")+" "+dataMap.get("ping"));
    }
}
