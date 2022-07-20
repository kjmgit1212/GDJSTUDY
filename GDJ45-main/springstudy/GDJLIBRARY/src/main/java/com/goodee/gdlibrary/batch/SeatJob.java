package com.goodee.gdlibrary.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.gdlibrary.service.SeatService;



@Component  // 안녕. 난 bean이야.
public class SeatJob {

   @Autowired
   private SeatService seatService;

   
   @Scheduled(cron="0 0/1 * * * ?")
      public void execute() {
      seatService.downSeatStatusByScheduled();
   }
   
}