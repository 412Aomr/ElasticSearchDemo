package com.aqh.conf;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: LHL
 * @Date: 2018/10/10 08:55
 * @Description:  ES 连接  配置
 */
@Configuration
public class ESConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ESConfig.class);

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        LOGGER.info("Elasticsearch初始化开始。。。。。");

        TransportClient transportClient = null;
        try {
            //es集群连接
            TransportAddress node = new TransportAddress(
                    InetAddress.getByName("192.168.0.128"),
                    9300
            );
            TransportAddress node1 = new TransportAddress(
                    InetAddress.getByName("192.168.0.128"),
                    9301
            );
            TransportAddress node2 = new TransportAddress(
                    InetAddress.getByName("192.168.0.128"),
                    9302
            );
            //es集群配置（自定义配置） 连接自己安装的集群名称
            Settings settings = Settings.builder()
                    .put("cluster.name", "ESCluster")
                    //增加嗅探机制，找到ES集群  如果报org.elasticsearch.transport.ReceiveTimeoutTransportException: 把他注释掉 即可
                    .put("client.transport.sniff", true)
                    //增加线程池个数，暂时设为5
                    .put("thread_pool.search.size", Integer.parseInt("5"))
                    .build();
            transportClient = new PreBuiltTransportClient(settings);
            transportClient.addTransportAddress(node);
            transportClient.addTransportAddress(node1);
            transportClient.addTransportAddress(node2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            LOGGER.error("elasticsearch TransportClient create error!!", e);
        }
        return transportClient;
    }
}
