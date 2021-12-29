package pers.mihao.careerism.service.mq.api;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import pers.mihao.common.util.ScanUtil;
import pers.mihao.common.util.log.Log;

/**
 * @Author mh32736
 * @Date 2021/11/26 11:22
 */
public class RabbitMqApiTest {

    private static final String TASK_QUEUE_NAME = "TASK_QUEUE_NAME";

    private static final String EXCHANGE_NAME = "TASK_EXCHANGE_NAME";

    static final String ROUTING_KEY = "ROUTING_KEY";


    public static void main(String[] args) throws Exception {
        setConsumer("消费者1");
        setConsumer("消费者2");
        createProducer();
    }

    private static void createProducer() throws IOException, TimeoutException {
        Connection connection = getConnection();
        // 声明此队列并且持久化
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic", false, false, null);
        ScanUtil.runFor(s -> {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, s.getBytes());
        });
        channel.close();
        connection.close();
    }

    private static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.20.29.212,10.20.29.214");
        factory.setPort(5672);
        factory.setUsername("rabbitmq");
        factory.setPassword("rabbitmq123");
        factory.setVirtualHost("/");
        return factory.newConnection();
    }

    public static void setConsumer(String name) throws Exception {
        Connection conn = getConnection();
        // 创建消息通道
        Channel channel = conn.createChannel();
        // 声明队列
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        // 绑定队列和交换机
        channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                Log.info("{}收到信息”{}", name, msg);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 开始获取消息 第2个参数表示是否自动ack
        // 应答模式是消费者端控制的
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
    }

}
