package br.com.ninb.moperbatch.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class Example implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Key: " + jobKey + " Executado em: " + new Date() + " Mensagem: " + context.getJobDetail().getJobDataMap().getString("TESTE") );
		
	}
	

	
	
}
