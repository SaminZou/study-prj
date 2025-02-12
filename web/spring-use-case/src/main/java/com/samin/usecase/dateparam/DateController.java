package com.samin.usecase.dateparam;

import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DateController {

    @PostMapping("/date/req")
    public void dateReq(@RequestBody DateReq req) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("时间段： {} ~ {}", req.getStartTime()
                                       .format(dtf), req.getEndTime()
                                                        .format(dtf));
    }
}
