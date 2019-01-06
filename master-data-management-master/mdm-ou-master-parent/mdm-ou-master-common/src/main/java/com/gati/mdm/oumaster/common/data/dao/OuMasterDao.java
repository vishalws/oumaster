package com.gati.mdm.oumaster.common.data.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gati.mdm.common.exception.business.ResourceAlreadyFoundException;
import com.gati.mdm.common.exception.business.ResourceNotFoundException;
import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.data.entity.QOuMaster;
import com.gati.mdm.oumaster.common.data.projection.OuMasterProjection;
import com.gati.mdm.oumaster.common.data.projection.OuMasterProjectionFactory;
import com.gati.mdm.oumaster.common.data.repository.jpa.OuMasterRepository;
import com.gati.mdm.oumaster.common.data.util.WhereClauseBuilder;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class OuMasterDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(OuMasterDao.class);

    private final OuMasterRepository ouMasterRepository;

    private final OuMasterProjectionFactory ouMasterProjectionFactory;

    private JPAQueryFactory queryFactory;

    @Autowired
    public OuMasterDao(JPAQueryFactory queryFactory, OuMasterRepository ouMasterRepository,
            OuMasterProjectionFactory ouMasterProjectionFactory) {
        super();
        this.ouMasterRepository = ouMasterRepository;
        this.ouMasterProjectionFactory = ouMasterProjectionFactory;
        this.queryFactory = queryFactory;
    }

    public List<OuMasterProjection> findOuMasterByCriteria(OuMasterListRequest request) {
        QOuMaster ouMaster = QOuMaster.ouMaster;
        BooleanBuilder conditions = new WhereClauseBuilder().build(request);
        List<OuMasterProjection> ouMasterList = queryFactory.select(ouMasterProjectionFactory)
                                                            .from(ouMaster)
                                                            .where(conditions.getValue())
                                                            .fetch();

        LOGGER.debug("findOuMasterByCriteria ouMasterList : {}", ouMasterList);
        return ouMasterList;
    }

    public OuMaster findOuMasterByOuCodeAndCompanyId(String ouCode, Integer companyId) {

        OuMaster ouMaster = ouMasterRepository.findByOuCodeAndCompanyId(ouCode, companyId);
        LOGGER.debug("findOuMasterByOuCodeAndCompanyId OuMaster: {}", ouMaster);
        return ouMaster;
    }

    public OuMaster createOuMaster(OuMaster ouMaster) {
        LOGGER.debug("createOuMaster OuMaster: {}", ouMaster);
        OuMaster dbOuMaster = ouMasterRepository.findByOuCodeAndCompanyId(ouMaster.getOuCode(),
                ouMaster.getCompanyId());
        OuMaster savedOuMaster = null;
        if (dbOuMaster == null) {
            LOGGER.debug("createOuMaster OuMaster NOT found in the DB. Proceeding to create ...");
            try {
                savedOuMaster = ouMasterRepository.save(ouMaster);
            } catch (Exception exception) {
                LOGGER.error("Exception occurred during save OuMaster", exception);
                throw exception;
            }
            LOGGER.debug("createOuMaster Successfully persisted OuMaster: {}", savedOuMaster);
        } else {
            String message = String.format("Operational Unit with Code '%s' and CompanyId '%d' already exists",
                    ouMaster.getOuCode(), ouMaster.getCompanyId());
            throw new ResourceAlreadyFoundException(message);
        }
        return savedOuMaster;
    }

    public OuMaster updateOuMaster(OuMaster ouMaster) {
        LOGGER.debug("updateOuMaster OuMaster: {}", ouMaster);
        OuMaster dbOuMaster = ouMasterRepository.findByOuCodeAndCompanyId(ouMaster.getOuCode(),
                ouMaster.getCompanyId());

        if (dbOuMaster == null) {
            String message = String.format("Operational Unit with Code '%s' and CompanyId '%d' does NOT exist",
                    ouMaster.getOuCode(), ouMaster.getCompanyId());
            throw new ResourceNotFoundException(message);

        }
        ouMaster.setId(dbOuMaster.getId());
        ouMasterRepository.save(ouMaster);

        OuMaster savedOuMaster = ouMasterRepository.save(ouMaster);
        LOGGER.debug("updateOuMasterByOuCodeAndCompanyId Successfully persisted OuMaster: {}", savedOuMaster);
        return savedOuMaster;
    }

}
