package com.errday.kafkahelper.adapter.out.kafka;

import com.errday.kafkahelper.domain.KafkaBootStrapServer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaAdminClient {

    public AdminClient kafkaAdminClient(KafkaBootStrapServer bootstrapServer) {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer.address());
        return AdminClient.create(config);
    }
}
