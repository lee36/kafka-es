package com.lee.kafkademo.dao;

import com.lee.kafkademo.model.JemeterData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JemterRepository extends ElasticsearchRepository<JemeterData,String> {
}
