package br.com.ninb.moperbatch;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import br.com.ninb.moperbatch.jobs.Example;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.ScheduleBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class MoperBatch {

	public static void main(String[] args) throws SchedulerException {

		String crontab = "15 0/2 * * * ?";
		
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sch = sf.getScheduler();

		JobDetail job = newJob(Example.class).withIdentity("Job1", "Group1")
				.build();

		job.getJobDataMap().put("TESTE", "SEJA BEM VINDO" );
		
		Trigger trigger = newTrigger().withSchedule(cronSchedule(crontab)).build();
		
		Trigger trigger1 = newTrigger()
				.startAt(new Date())
				.withSchedule(simpleSchedule()
						.withRepeatCount(4)
						.withIntervalInSeconds(15)
						).build();
		


		
		sch.scheduleJob(job, trigger);
		sch.scheduleJob(job, trigger1);
		
		sch.start();
		
	}

	
	
}
