package com.gati.mdm.oumaster.processor.kafka;

import com.gati.mdm.avro.model.ou.OuMasterEvent;

public interface OuMasterSinkStreamConsumer {

    void processCreateEventInput(OuMasterEvent ouMasterEvent, String topic, int partition, int offset,
            String userName);

    void processUpdateEventInput(OuMasterEvent ouMasterEvent, String topic, int partition, int offset,
            String userName);

    void processDeactivateEventInput(OuMasterEvent ouMasterEvent, String topic, int partition,
            int offset, String userName);

}
