package com.gammastack.backgroundjob.services;

import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserJob {
  @Autowired
  private JobScheduler jobScheduler;

  public void sendWelcomeEmail(){
    //Fire-Forget Jobs
    JobId jobId = jobScheduler.enqueue(() -> System.out.println("-------Welcome Aboard User------"));
    System.out.println("---Welcome Email Job ID:"+ jobId +"------");
  }

  public void sendVerificationEmail(){
    //Scheduling Jobs in the Future
    JobId jobId = jobScheduler.schedule(LocalDateTime.now().plusMinutes(1), () -> System.out.println("-------Verify your email------"));
    System.out.println("---Triggered Verification Email Job ID:"+ jobId +"------");
  }

  public void sendReviewReminderEmail(){
    //Scheduling Jobs Recurrently
    jobScheduler.scheduleRecurrently(Cron.minutely(), () -> System.out.println("-------Review reminder email------"));
    System.out.println("---Triggered Review Reminder Email Job ID:------");
  }
}

