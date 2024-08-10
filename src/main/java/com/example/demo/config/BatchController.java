package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    @GetMapping("/run-batch")
    public String runBatch(@RequestParam String fileName) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("fileName", fileName)
                    .toJobParameters();

            jobLauncher.run(importJob, jobParameters);

            return "Batch job has been invoked!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Batch job failed!";
        }
    }
}
