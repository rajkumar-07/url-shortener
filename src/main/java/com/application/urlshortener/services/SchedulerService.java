package com.application.urlshortener.services;

import com.application.urlshortener.dao.URLInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private final URLInfoRepository urlInfoRepository;

    @Scheduled(fixedRate = 6000)
    public void deleteExpiredUrls(){
        urlInfoRepository.deleteExpiredData(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
    }
}
