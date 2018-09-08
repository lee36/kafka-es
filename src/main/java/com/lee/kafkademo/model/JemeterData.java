package com.lee.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "test",type = "jemter")
public class JemeterData {
    @Id
    private String timeStamp;
    private String elapsed;
    private String label;
    private String responseCode;
    private String responseMessage;
    private String threadName;
    private String dataType;
    private String success;
    private String failureMessage;
    private String bytes;
    private String sentBytes;
    private String grpThreads;
    private String allThreads;
    private String Latency;
    private String IdleTime;
    private String Connect;
}
