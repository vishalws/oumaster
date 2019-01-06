package com.gati.mdm.oumaster.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.gati.mdm.avro.model.ou.EventType;
import com.gati.mdm.avro.model.ou.OuMasterEvent;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.kafka.channel.OuMasterSource;
import com.gati.mdm.oumaster.common.mapper.OuMasterEventMapper;

@Component
@EnableBinding(OuMasterSource.class)
public class OuMasterSourceStreamProducerImpl implements OuMasterSourceStreamProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OuMasterSourceStreamProducerImpl.class);

    private static final long SEND_BUS_TIMEOUT = 3000;

    private OuMasterSource ouMasterSource;

    private OuMasterEventMapper ouMasterEventMapper;

    @Autowired
    public OuMasterSourceStreamProducerImpl(OuMasterSource ouMasterSource, OuMasterEventMapper ouMasterEventMapper) {
        this.ouMasterSource = ouMasterSource;
        this.ouMasterEventMapper = ouMasterEventMapper;
    }

    @Override
    public void sendCreateOuMasterEvent(OuMaster ouMaster) {
        OuMasterEvent ouMasterEvent = ouMasterEventMapper.mapToOuMasterEvent(ouMaster);
        LOGGER.debug("sendCreateOuMasterEvent mapped OuMasterEvent:{}", ouMasterEvent);
        ouMasterEvent.setEventType(EventType.CREATE);
        LOGGER.debug("sendCreateOuMasterEvent final OuMasterEvent:{} after setting EventType", ouMasterEvent);
        sendToKafka(ouMasterEvent);
    }

    @Override
    public void sendUpdateOuMasterEvent(OuMaster ouMaster) {

        OuMasterEvent ouMasterEvent = ouMasterEventMapper.mapToOuMasterEvent(ouMaster);
        LOGGER.debug("sendUpdateOuMasterEvent mapped OuMasterEvent:{}", ouMasterEvent);
        ouMasterEvent.setEventType(EventType.UPDATE);
        LOGGER.debug("sendUpdateOuMasterEvent final OuMasterEvent:{} after setting EventType", ouMasterEvent);
        sendToKafka(ouMasterEvent);
    }

    @Override
    public void sendDeactivateOuMasterEvent(String ouCode) {

        //OuMasterEvent ouMasterEvent= ouMasterEventMapper.mapToOuMasterEvent(ouMaster);
        //ouMasterEvent.setEventType(eventType.DEACTIVATE);
        OuMasterEvent ouMasterEvent = OuMasterEvent.newBuilder()
                                                   .setEventType(EventType.DEACTIVATE)
                                                   .setOuCode(ouCode)
                                                   .build();
        sendToKafka(ouMasterEvent);
    }

    private void sendToKafka(OuMasterEvent ouMasterEvent) {
        // TODO: Take username from header of HTTP
        Message<OuMasterEvent> ouMasterEventBusMessage = MessageBuilder.withPayload(ouMasterEvent)
                                                                       .setHeader("partitionKey",
                                                                               ouMasterEvent.getOuCode())
                                                                       .setHeader("userName", "TESTUSER")
                                                                       .setHeader("operation",
                                                                               ouMasterEvent.getEventType()
                                                                                            .name())
                                                                       .build();

        LOGGER.debug("sendToKafka Kafka Message prepared to publish: {}", ouMasterEventBusMessage);
        boolean send = ouMasterSource.producerChannel()
                                     .send(ouMasterEventBusMessage, SEND_BUS_TIMEOUT);
        if (send) {
            LOGGER.info("sendToKafka Event sent to bus successfully Payload: {}", ouMasterEvent);
        } else {
            LOGGER.error("sendToKafka An error occurred while sending event: {} to bus.", ouMasterEvent);
            // TODO: throw exception
        }
    }

}
