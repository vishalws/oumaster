package com.gati.mdm.oumaster.common.data.projection;

import static com.gati.mdm.oumaster.common.data.entity.QOuMaster.ouMaster;

import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.MappingProjection;

@Component
public class OuMasterProjectionFactory extends MappingProjection<OuMasterProjection> {

    private static final long serialVersionUID = -6436008837947830869L;

    public OuMasterProjectionFactory() {
        super(OuMasterProjection.class, ouMaster.ouCode, ouMaster.companyId, ouMaster.reportingOu,
                ouMaster.ouCategoryCode, ouMaster.accountingOu, ouMaster.name, ouMaster.type, ouMaster.city,
                ouMaster.status, ouMaster.operationalStatus, ouMaster.ouHierarchyType, ouMaster.acClosedStatus,
                ouMaster.ouOnlineFlag, ouMaster.gatiJubileeFlag, ouMaster.sbuCode);
    }

    @Override
    protected OuMasterProjection map(Tuple row) {
        return ImmutableOuMasterProjection.builder()
                                          .ouCode(row.get(ouMaster.ouCode))
                                          .companyId(row.get(ouMaster.companyId))
                                          .reportingOu(row.get(ouMaster.reportingOu))
                                          .ouCategoryCode(row.get(ouMaster.ouCategoryCode))
                                          .accountingOu(row.get(ouMaster.accountingOu))
                                          .name(row.get(ouMaster.name))
                                          .type(row.get(ouMaster.type))
                                          .city(row.get(ouMaster.city))
                                          .status(row.get(ouMaster.status))
                                          .operationalStatus(row.get(ouMaster.operationalStatus))
                                          .ouHierarchyType(row.get(ouMaster.ouHierarchyType))
                                          .acClosedStatus(row.get(ouMaster.acClosedStatus))
                                          .ouOnlineFlag(row.get(ouMaster.ouOnlineFlag))
                                          .gatiJubileeFlag(row.get(ouMaster.gatiJubileeFlag))
                                          .sbuCode(row.get(ouMaster.sbuCode))
                                          .build();
    }
}
