package com.samin.jobsdk.itf;

import com.samin.jobsdk.exception.JobException;

public interface JobWorker {

    void action(String param) throws JobException;
}
