package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


@WebListener


public class StudentListener implements ServletContextListener {

	
	/*
	 * field
	 */
	private Scheduler scheduler;
	
	
    /*
     * Default constructor. 
     */
    public StudentListener() {
        try {
        	scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

	/*
     * contextDestroyed()
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
    		if(scheduler != null) {
    			scheduler.shutdown();
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	/*
		contextInitialized()
		
		cron expression(크론식)을 이용한 CronScheduler
		
		1. 구성
		    초 분 시 일 월 요일 [년도]
		
		2. 상세
		    1) 초 : 0~59
		    2) 분 : 0~59
		    3) 시 : 0~23
		    4) 일 : 1~31
		    5) 월 : 0~11, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
		    6) 요일 : 1(MON)~7(SUN), MON, TUE, WED, THR, FRI, SAT, SUN
		
		3. 작성방법
		    1) * : 매번
		    2) ? : 설정 안함(일, 요일에서 작성)
		    3) / : 주기
		      a/b : a부터 b마다 동작  0/10
		
		4. 작성예시
		    1) 10초마다             0/10 * * * * ?
		    2) 1분마다              0 0/1 * * * ?
		    3) 5분마다 & 10초 후    10 0/5 * * * ?  (10:00:10, 10:05:10, 10:10:10, ...)
		    4) 수요일 12시마다      0 0 12 ? * 3
		                            0 0 12 ? * WED
		    5) 수요일과 금요일       
		       10:30, 11:30, 12:30  0 30 10-12 ? * WED,FRI
		
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	try {
    		
    		// 1. Job 생성
    		JobDetail job = JobBuilder.newJob(StudentTop3Job.class)
    				.withIdentity("job3", "group3")
    				.build();
    		
    		// 2. Trigger 생성
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger3", "group3")
    				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
    				.build();
    		
    		// 3. scheduler에 Job, Trigger 등록
    		scheduler.scheduleJob(job, trigger);
    		
    		// 4. scheduler 시작
    		scheduler.start();
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
}
