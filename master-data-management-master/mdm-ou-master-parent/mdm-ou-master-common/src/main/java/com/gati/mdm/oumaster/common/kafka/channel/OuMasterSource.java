package com.gati.mdm.oumaster.common.kafka.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OuMasterSource {

    @Output(Channels.OU_MASTER_OUTPUT)
    MessageChannel producerChannel();
}
