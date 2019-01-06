package com.gati.mdm.oumaster.processor.kafka;

import static com.gati.mdm.oumaster.common.kafka.channel.Channels.OU_MASTER_INPUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.gati.mdm.avro.model.ou.OuMasterEvent;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.service.OuMasterService;
import com.gati.mdm.oumaster.common.kafka.channel.OuMasterSink;
import com.gati.mdm.oumaster.common.mapper.OuMasterEventMapper;

@Component
@EnableBinding(OuMasterSink.class)
@EnableAutoConfiguration
public class OuMasterSinkStreamConsumerImpl implements OuMasterSinkStreamConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(OuMasterSinkStreamConsumerImpl.class);

    private OuMasterService ouMasterService;

    private OuMasterEventMapper ouMasterEventMapper;

    @Autowired
    public OuMasterSinkStreamConsumerImpl(OuMasterService ouMasterService, OuMasterEventMapper ouMasterEventMapper) {
        this.ouMasterService = ouMasterService;
        this.ouMasterEventMapper = ouMasterEventMapper;
    }

    @Override
    @StreamListener(target = OU_MASTER_INPUT, condition = "headers['operation']=='CREATE'")
    public void processCreateEventInput(@Payload OuMasterEvent ouMasterEvent,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.OFFSET) int offset,
            @Header("userName") String userName) {
        LOGGER.info("Received CREATE OuMaster Event: {}. topic: {}, partition: {}, offset: {} User:{}", ouMasterEvent,
                topic, partition, offset, userName);

        // Already fields are converted YES - Y and dates with format
        // No need to do one more conversion here.

        OuMaster ouMaster = ouMasterEventMapper.mapToOuMaster(ouMasterEvent);
        ouMaster.setCreatedBy(userName);

        LOGGER.info("processOuMasterCreateEventInput Mapped OuMaster Event: {}", ouMaster);
        ouMasterService.createOuMaster(ouMaster);
    }

    @Override
    @StreamListener(target = OU_MASTER_INPUT, condition = "headers['operation']=='UPDATE'")
    public void processUpdateEventInput(@Payload OuMasterEvent ouMasterEvent,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.OFFSET) int offset,
            @Header("userName") String userName) {

        LOGGER.info("Received UPDATE OuMaster Event: {}. topic: {}, partition: {}, offset: {} User:{}", ouMasterEvent,
                topic, partition, offset, userName);
        OuMaster dbOuMaster = ouMasterService.getOuMasterByOuCodeAndCompanyId(String.valueOf(ouMasterEvent.getOuCode()),
                ouMasterEvent.getCompanyId());

        // Need to Copy only the NonNull fields
        ouMasterEventMapper.updateOuMasterFromEvent(ouMasterEvent, dbOuMaster);
        dbOuMaster.setLastUpdatedBy(userName);
        LOGGER.debug("Mapped OuMaster after merging with OuMasterEvent:{}", dbOuMaster);
        ouMasterService.updateOuMaster(dbOuMaster);
    }

    @Override
    @StreamListener(target = OU_MASTER_INPUT, condition = "headers['operation']=='DEACTIVATE'")
    public void processDeactivateEventInput(@Payload OuMasterEvent ouMasterEvent,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition, @Header(KafkaHeaders.OFFSET) int offset,
            @Header("userName") String userName) {
        LOGGER.info("Received DEACTIVATE OuMaster Event: {}. topic: {}, partition: {}, offset: {} User:{}",
                ouMasterEvent, topic, partition, offset, userName);
        OuMaster ouMaster = ouMasterEventMapper.mapToOuMaster(ouMasterEvent);

        ouMasterService.deactivateOuMaster(ouMaster);
    }

}