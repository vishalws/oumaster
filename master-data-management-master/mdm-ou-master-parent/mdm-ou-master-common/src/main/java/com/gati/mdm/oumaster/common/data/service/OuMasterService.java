package com.gati.mdm.oumaster.common.data.service;

import java.util.List;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.projection.OuMasterProjection;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;

public interface OuMasterService {

    OuMaster createOuMaster(OuMaster ouMaster);

    OuMaster updateOuMaster(OuMaster ouMaster);

    OuMaster deactivateOuMaster(OuMaster ouMaster);

    OuMaster getOuMasterByOuCodeAndCompanyId(String ouCode, Integer companyId);

    OuMaster validateAndGetOuMasterByOuCodeAndCompanyId(String ouCode, Integer companyId);

    OuMaster validateOuMasterExistsByOuCodeAndCompanyId(String ouCode, Integer companyId);

    boolean validateAndCheckIfOuMasterChanged(OuMaster referenceOuMaster, OuMaster target);

    List<OuMasterProjection> getOuMasterByCriteria(OuMasterListRequest request);

}
