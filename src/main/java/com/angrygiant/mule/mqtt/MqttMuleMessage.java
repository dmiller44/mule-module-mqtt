package com.angrygiant.mule.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * Created with IntelliJ IDEA.
 * User: dmiller
 * Date: 9/21/12
 * Time: 9:57 AM
 *
 * Class that contains all the pieces of a MQTT Message (Message, Topic)
 */
public class MqttMuleMessage {
    private final MqttMessage message;

    private final MqttTopic topic;

    public MqttMuleMessage(final MqttMessage message, final MqttTopic topic) {
        this.message = message;
        this.topic = topic;
    }

    public MqttMessage getMessage() {
        return message;
    }

    public MqttTopic getTopic() {
        return topic;
    }
}
