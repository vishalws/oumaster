package com.gati.mdm.oumaster.common.data.specification;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.gati.mdm.oumaster.common.data.entity.OuMaster;
import com.gati.mdm.oumaster.common.dto.OuMasterListRequest;

@Component
public class OuMasterSpecification extends BaseSpecification<OuMaster, OuMasterListRequest> {

    public static final Logger LOGGER = LoggerFactory.getLogger(OuMasterSpecification.class);

    @Override
    public Specification<OuMaster> getFilter(OuMasterListRequest request) {
        LOGGER.debug("Entering getFilter with OuMasterListRequest:{}", request);
        return (root, query, cb) -> {
            if (StringUtils.isBlank(request.getOuCode())) {
                return Specification.where(nameContains(request.getName()))
                                    .and(operationalStatusEquals(request.getOperationalStatus()))
                                    .and(reportingOuEquals(request.getReportingOu()))
                                    .and(gatiJubileeFlagEquals(request.getGatiJubileeFlag()))
                                    .toPredicate(root, query, cb);
            } else {
                return Specification.where(ouCodeEquals(request.getOuCode()))
                                    .and(companyIdEquals(request.getGatiJubileeFlag()))
                                    .toPredicate(root, query, cb);
            }
        };
    }

    private Specification<OuMaster> nameContains(String name) {
        return entityAttributeContains("name", name);
    }

    private Specification<OuMaster> operationalStatusEquals(String operationalStatus) {
        return entityAttributeEquals("operationalStatus", operationalStatus);
    }

    private Specification<OuMaster> reportingOuEquals(String reportingOu) {
        return entityAttributeEquals("reportingOu", reportingOu);
    }

    private Specification<OuMaster> ouCodeEquals(String ouCode) {
        return entityAttributeEquals("ouCode", ouCode);
    }

    private Specification<OuMaster> companyIdEquals(String companyId) {
        return entityAttributeEquals("companyId", companyId);
    }

    private Specification<OuMaster> gatiJubileeFlagEquals(String gatiJubileeFlag) {
        return entityAttributeEquals("gatiJubileeFlag", gatiJubileeFlag);
    }
}