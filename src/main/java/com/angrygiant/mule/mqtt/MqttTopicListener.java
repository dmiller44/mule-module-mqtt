package com.angrygiant.mule.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.mule.api.callback.SourceCallback;

/**
 * Created with IntelliJ IDEA.
 * User: dmiller
 * Date: 9/21/12
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MqttTopicListener implements MqttCallback {
    private static final Logger logger = Logger.getLogger(MqttTopicListener.class);

    private final MqttClient client;
    private final SourceCallback callback;
    private String topicName;
    private int qoh;

    public MqttTopicListener(MqttClient client, SourceCallback callback, String topicName) {
        this(client, callback, topicName, 2);
    }

    public MqttTopicListener(MqttClient client, SourceCallback callback, String topicName, int qoh) {
        this.client = client;
        this.callback = callback;
        this.topicName = topicName;
        this.qoh = qoh;
    }

    public void connectionLost(Throwable throwable) {
        logger.error("AH! You got throw off the broker!", throwable);
    }

    public void messageArrived(MqttTopic mqttTopic, MqttMessage mqttMessage) throws Exception {
        logger.info("Creating new Mule message - got something on " + mqttTopic.getName());
        MqttMuleMessage message = new MqttMuleMessage(mqttMessage, mqttTopic);

        this.callback.process(message);
    }

    public void deliveryComplete(MqttDeliveryToken mqttDeliveryToken) {
        logger.trace("Message Delivered");
    }
}
