package com.gati.mdm.oumaster.service.kafka;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;

public interface OuMasterSourceStreamProducer {

    void sendCreateOuMasterEvent(OuMaster ouMaster);

    void sendUpdateOuMasterEvent(OuMaster ouMaster);

    void sendDeactivateOuMasterEvent(String ouCode);
}
