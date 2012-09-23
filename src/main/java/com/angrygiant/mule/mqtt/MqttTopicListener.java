package com.angrygiant.mule.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.mule.api.callback.SourceCallback;

/**
 * Created with IntelliJ IDEA.
 * User: dmiller
 * Date: 9/21/12
 * Time: 2:39 PM
 *
 * TopicListener is responsible for initiating and holding a connection to the MQTT broker for topic subscription.
 *
 */
public class MqttTopicListener implements MqttCallback {
    private static final Logger logger = Logger.getLogger(MqttTopicListener.class);

    private final MqttClient client;
    private final SourceCallback callback;
    private final MqttConnectOptions connectOptions;
    private String topicName;
    private int qos = 2;
    private long subscriptionDelay = 500;

    public MqttTopicListener(MqttClient client, SourceCallback callback, String topicName, MqttConnectOptions connectOptions, long subscriptionDelay) {
        this(client, callback, topicName, connectOptions, subscriptionDelay, 2);
    }

    public MqttTopicListener(MqttClient client, SourceCallback callback, String topicName, MqttConnectOptions connectOptions, long subscriptionDelay, int qos) {
        this.client = client;
        this.callback = callback;
        this.topicName = topicName;
        this.connectOptions = connectOptions;
        this.subscriptionDelay = subscriptionDelay;
        this.qos = qos;
    }

    public void connectionLost(Throwable throwable) {
        logger.warn("AH! You got throw off the broker! - Attempting to reconnect...");
        logger.trace(throwable);

        if (!canReconnect()) {
            logger.error("Can't reconnect to broker! No Messages will be received!");
        }
    }

    private boolean canReconnect() {
        boolean reconnected = false;

        if (!client.isConnected()) {
            try {
                logger.debug("Setting callback method to myself...");
                client.setCallback(this);

                logger.debug("Connecting with connection options provided from mqtt:config element...");
                client.connect(connectOptions);

                logger.debug("Sleeping to waiting for established connection...");
                Thread.sleep(subscriptionDelay);

                logger.debug("Subscribing to topic " + topicName + " with QOS of " + qos);
                client.subscribe(this.topicName, this.qos);

                logger.debug("Reconnection was successful - setting flag to true");
                reconnected = true;
            } catch (MqttException e) {
                logger.error("MQTT Error while reconnecting for subscription: ", e);
            } catch (InterruptedException e) {
                logger.error("Sleep Error while reconnecting for subscription: ", e);
            }
        }

        logger.debug("Reconnection is determined as " + (client.isConnected() && reconnected));
        return client.isConnected() && reconnected;
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
