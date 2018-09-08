package com.lee.kafkademo;

import com.lee.kafkademo.dao.JemterRepository;
import com.lee.kafkademo.model.JemeterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@SpringBootApplication
@EnableKafka
@RestController
@EnableAsync
@EnableElasticsearchRepositories
public class KafkaDemoApplication {
    @Autowired
    private JemterRepository jemterRepository;
    @Autowired
    private KafkaTemplate template;
    @RequestMapping("/test")
    public Object test() throws IOException {
        FileInputStream fis = new FileInputStream("getConfigData.jtl");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String temp=null;
        while((temp=br.readLine())!=null){
            template.send("test",temp);
        }
        return "success";
    }
    @KafkaListener(topics="test",groupId = "g1")
    @Async
    public void recive1(String text) throws InterruptedException {
        String[] split = text.split(",");
        System.out.println(text);
        JemeterData data = new JemeterData(split[0],split[1],split[2],
                split[3],split[4],split[5],split[6],split[7],split[8],
                split[9],split[10],split[11],split[12],split[13],split[14],split[15]);
        jemterRepository.save(data);
        System.out.println(111);

    }
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }
}
