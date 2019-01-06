package com.gati.mdm.oumaster.common.data.service;

import java.util.List;

import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gati.mdm.common.exception.business.ResourceAlreadyFoundException;
import com.gati.mdm.common.exception.business.ResourceNotChangedException;
import com.gati.mdm.common.exception.business.ResourceNotFoundException;
import com.gati.mdm.oumaster.common.data.dao.OuMasterDao;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.projection.OuMasterProjection;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;

@Service
public class OuMasterServiceImpl implements OuMasterService {

    public static final Logger LOGGER = LoggerFactory.getLogger(OuMasterService.class);

    private final OuMasterDao ouMasterDao;

    private final Javers javers;

    @Autowired
    public OuMasterServiceImpl(OuMasterDao ouMasterDao, Javers javers) {
        super();
        this.ouMasterDao = ouMasterDao;
        this.javers = javers;
    }

    @Override
    public List<OuMasterProjection> getOuMasterByCriteria(OuMasterListRequest request) {
        LOGGER.debug("Entering getOuMasterByCriteria with OuMasterListRequest:{}", request);
        List<OuMasterProjection> ouMasterList = ouMasterDao.findOuMasterByCriteria(request);

        LOGGER.debug("getOuMasterByCriteria with Response:{}", ouMasterList);
        return ouMasterList;
    }

    @Override
    public OuMaster getOuMasterByOuCodeAndCompanyId(String ouCode, Integer companyId) {
        LOGGER.debug("Entering getOuMasterByOuCodeAndCompanyId OuCode:{} CompanyId:{}", ouCode, companyId);
        return ouMasterDao.findOuMasterByOuCodeAndCompanyId(ouCode, companyId);

    }

    @Override
    public OuMaster validateAndGetOuMasterByOuCodeAndCompanyId(String ouCode, Integer companyId) {
        LOGGER.debug("Entering validateAndGetOuMasterByOuCodeAndCompanyId OuCode:{} CompanyId:{}", ouCode, companyId);
        OuMaster ouMaster = getOuMasterByOuCodeAndCompanyId(ouCode, companyId);
        if (ouMaster == null) {
            String message = String.format("Operational Unit with Code '%s' and CompanyId '%d' does NOT exist", ouCode,
                    companyId);
            throw new ResourceNotFoundException(message);

        }
        return ouMaster;
    }

    @Override
    public OuMaster validateOuMasterExistsByOuCodeAndCompanyId(String ouCode, Integer companyId) {
        LOGGER.debug("Entering validateOuMasterExistsByOuCodeAndCompanyId OuCode:{} CompanyId:{}", ouCode, companyId);
        OuMaster ouMaster = getOuMasterByOuCodeAndCompanyId(ouCode, companyId);
        if (ouMaster != null) {
            String message = String.format("Operational Unit with Code '%s' and CompanyId '%d' already exists", ouCode,
                    companyId);
            throw new ResourceAlreadyFoundException(message);

        }
        LOGGER.debug("validateOuMasterExistsByOuCodeAndCompanyId Got NULL OuMaster:{} ", ouMaster);
        return ouMaster;
    }

    @Override
    public boolean validateAndCheckIfOuMasterChanged(OuMaster dbOuMaster, OuMaster newOuMaster) {
        LOGGER.debug("Entering validateAndCheckIfOuMasterChanged referenceOuMaster: {} targetOuMaster:{}", dbOuMaster,
                newOuMaster);

        Diff diff = null;
        try {
            diff = javers.compare(dbOuMaster, newOuMaster);
        } catch (Exception exception) {
            LOGGER.error("Exception occurred validateAndCheckIfOuMasterChanged", exception);
        }

        LOGGER.debug("validateAndCheckIfOuMasterChanged diff:{}", diff);

        if (diff != null && diff.hasChanges()) {
            LOGGER.debug("Changes made to OuMaster:[");
            diff.getChanges()
                .forEach(change -> LOGGER.debug("- {}", change));
            LOGGER.debug("]");
            return true;
        } else {
            String message = String.format("No Changes were made to Operational Unit with Code '%s' to save",
                    dbOuMaster.getOuCode());
            throw new ResourceNotChangedException(message);
        }
    }

    @Override
    public OuMaster createOuMaster(OuMaster ouMaster) {
        LOGGER.debug("Entering createOuMaster OuMaster: {}", ouMaster);
        return ouMasterDao.createOuMaster(ouMaster);
    }

    @Override
    @Transactional
    public OuMaster updateOuMaster(OuMaster ouMaster) {
        LOGGER.debug("Entering updateOuMaster OuMaster: {}", ouMaster);

        return ouMasterDao.updateOuMaster(ouMaster);
    }

    @Override
    public OuMaster deactivateOuMaster(OuMaster ouMaster) {
        return null;
    }
}
