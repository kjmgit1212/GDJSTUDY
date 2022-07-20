package ex02.tomcat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 * ServletContextListener 인터페이스
 * 
 * 1. 웹 애플리케이션의 Lifecycle로 동작한다.
 * 2. 시작할 때 contextInitialized() 메소드가 동작한다.
 * 3. 종료될 때 contextDestroyed() 메소드가 동작한다.
 */

@WebListener  // 나는 리스너이다.

public class MyListener implements ServletContextListener {

	/* field */
	private Scheduler scheduler;
	
    /*
     * Default constructor.
     * 
     * Scheduler scheduler 생성하기
     */
    public MyListener() {
    	try {
    		scheduler = StdSchedulerFactory.getDefaultScheduler();
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/*
     * contextDestroyed()
     * 
     * 웹 애플리케이션이 종료될 때 Scheduler 인스턴스 종료 
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
    		if(scheduler != null) {
    			scheduler.shutdown();
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/*
     * contextInitialized()
     * 
     * 웹 애플리케이션이 시작할 때 Scheduler 인스턴스 시작(Job, Trigger)
     */
    public void contextInitialized(ServletContextEvent sce)  { 

    	try {
    		
    		// 1. Scheduler가 동작 시킬 Job 생성
    		JobDetail job = JobBuilder.newJob(MyJob.class)
    				.withIdentity("job2", "group2")
    				.build();
    		
    		// 2. Scheduler가 동작할 시점 Trigger 생성
    		Trigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("trigger2", "group2")
    				.startNow()
    				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
    						.withIntervalInSeconds(10)
    						.repeatForever())
    				.build();
    		
    		// 3. Scheduler 인스턴스에 Job, Trigger 등록
    		scheduler.scheduleJob(job, trigger);
    		
    		// 4. Scheduler 인스턴스 시작
    		// scheduler.start();
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
}
