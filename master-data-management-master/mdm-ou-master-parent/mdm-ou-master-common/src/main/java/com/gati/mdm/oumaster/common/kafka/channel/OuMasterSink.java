package com.gati.mdm.oumaster.common.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OuMasterSink {

    @Input(Channels.OU_MASTER_INPUT)
    SubscribableChannel consumerChannel();
}
