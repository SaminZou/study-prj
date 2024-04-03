package com.samin.jobsdk.itf;

import com.samin.jobsdk.exception.JobException;

public interface JobWorker {

    String action() throws JobException;
}
