package com.angrygiant.mule.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 * Mule MQTT Module
 * <p/>
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * <p/>
 * <p>
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 * </p>
 * <p>
 * Created with IntelliJ IDEA.
 * User: dmiller@angrygiant.com
 * Date: 9/21/12
 * Time: 9:57 AM
 * </p>
 * <p>
 * Class that contains all the pieces of a MQTT Message (Message, Topic)
 * </p>
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
