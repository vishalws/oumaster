package com.gati.mdm.oumaster.common.data.projection;

import javax.annotation.Nullable;

import org.immutables.value.Value;

@Value.Immutable
public interface OuMasterProjection {

    String getOuCode();

    Integer getCompanyId();

    String getOperationalStatus();

    String getOuHierarchyType();

    String getOuOnlineFlag();

    String getGatiJubileeFlag();

    @Nullable
    String getReportingOu();

    @Nullable
    String getOuCategoryCode();

    @Nullable
    String getAccountingOu();

    @Nullable
    String getName();

    @Nullable
    String getType();

    @Nullable
    String getCity();

    @Nullable
    String getStatus();

    @Nullable
    String getAcClosedStatus();

    @Nullable
    String getSbuCode();

}
