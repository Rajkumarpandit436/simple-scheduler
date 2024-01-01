package simplescheduler;

import jakarta.annotation.PostConstruct;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RequestMapping("/schedule-job")
@RestController
public class ScheduleJobController {

    @Autowired
    private ScheduleJob scheduleJob;

    @PostConstruct
    public void schedule() throws SchedulerException {

        for (int i=0; i<10; i++){
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("name", "raju");
            dataMap.put("address", "address1");
            dataMap.put("ping", "pong");
            scheduleJob.scheduleJob("job"+Math.random(), "job-group-scheduler",
                    "0 "+(21+i)+" 12 * * ? ", "initial-job", "my-trigger"+Math.random(), dataMap);
        }
    }

    @PostMapping("/schedule/{jon-name}/{cid}")
    public void createScheduler(@PathVariable("jon-name") String jobName, @PathVariable("cid") int cid){
        System.out.println("JOB-test");
    }
}
