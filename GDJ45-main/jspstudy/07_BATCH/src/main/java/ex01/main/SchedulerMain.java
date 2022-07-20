package ex01.main;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerMain {

	public static void main(String[] args) {
		
		try {
			
			// Scheduler 생성
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			// Scheduler 인스턴스가 처리할 Job 생성
			JobDetail job = JobBuilder.newJob(HelloJob.class)
					.withIdentity("job1", "group1")  // 개발자가 정하면 됨.
					.build();
			
			// Scheduler 인스턴스가 동작할 시점 생성(SimpleScheduleBuilder)
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")  // 이름 trigger1(개발자 마음대로), 그룹 group1은 Job의 그룹과 맞추기
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(5)
							.repeatForever())
					.build();
			
			// Scheduler 인스턴스에 동작할 Job과 동작할 시점 Trigger 알려주기
			scheduler.scheduleJob(job, trigger);
			
			// Scheduler 인스턴스 동작 시작
			scheduler.start();
			
			// 30초 sleep 일시중지
			// main 스레드가 30초 일시중지하는 동안
			// scheduler가 계속 동작한다.(scheduler는 별도의 스레드이다.)
			Thread.sleep(30000);  // 30000 밀리초 = 30초
			
			// Scheduler 인스턴스 동작 중지
			scheduler.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
