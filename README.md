
SUMMARY
=======
The [MQTT][mqtt] module was created as another option of communication for the Mule ESB.  [MQTT][mqtt] is a protocol typically
used in M2M communication, which is tolerant of environments where the client device could get disconnected or
experience a high latency situation.

[MQTT][mqtt] is similar to many other messaging protocols, such as AMQP and JMS.  Communication occurs over a "topic", and
has a given Quality of Service (QOS) setting that ranges from "fire and forget" to "guaranteed delivery".

TESTING
=======

Test cases are still being worked on in this module.  Please see the "Quick Start" section to get a working environment
set up locally.

QUICK START
===========

Requirements:  An MQTT Broker (such as [Mosquitto][mosquitto]), or a freely available public server (list of servers found at http://mqtt.org/wiki/doku.php/public_brokers)

1.  Ensure that you have a broker up and running, and are capable of passing messages via a given topic.
2.  Ensure you have a running Mule ESB instance.  The module has only been tested on Mule ESB v3.2.2
3.  Install the MQTT Module into the ESB, or into your Mule application.
4.  Create a Mule application. (Either via Mule Studio, or Maven:  [http://www.mulesoft.org/documentation/display/MULE3USER/Creating+Project+Archetypes])
5.  Install the `mqtt` namespace into your application.
6.  Configure your Mule MQTT module to point to your broker, using the `<mqtt:config />` object.
7.  Create a new Mule flow.  If this flow is subscribing to incoming MQTT messages, use the `<mqtt:subscribe />` endpoint.  To publish, use `<mqtt:publish />`.
8.  Compile and deploy your application to Mule, ensuring the application starts appropriately.
9.  Finally, start sending and receiving messages via MQTT!!

To see a full, in-depth howto, please visit my blog @ [http://dnbmiller.wordpress.com][blog].

ADDITIONAL RESOURCES
====================

* Author:  Daniel Miller (dmiller@angrygiant.com)
* Twitter:  @hockeymann44
* Source Code:  [http://github.com/dmiller44/mule-module-mqtt][source]
* Documentation:  [http://dmiller44.github.com/mule-module-mqtt/mule/mqtt.html][doc]
* MQTT website:  [http://mqtt.org][mqtt]
* Eclipse Paho Client (on which the module is based on):  [https://github.com/eclipse/paho.mqtt.java][paho]
* My Blog:  [http://dnbmiller.wordpress.com][blog]




[blog]:http://dnbmiller.wordpress.com
[mqtt]:http://mqtt.org/
[paho]:https://github.com/eclipse/paho.mqtt.java
[doc]:http://dmiller44.github.com/mule-module-mqtt/mule/mqtt.html
[source]:http://github.com/dmiller44/mule-module-mqtt
[mosquitto]:http://mosquitto.org/

