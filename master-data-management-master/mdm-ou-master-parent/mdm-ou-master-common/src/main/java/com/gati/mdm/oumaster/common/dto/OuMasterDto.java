package com.gati.mdm.oumaster.common.dto;

import java.util.Date;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(allParameters = true)
public interface OuMasterDto {

    String getOuCode();

    Integer getCompanyId();

    String getStateCode();

    String getCurrencyCode();

    String getReportingOu();

    String getOuCategoryCode();

    String getAccountingOu();

    String getPincode();

    String getCountryCode();

    String getStaffCode();

    String getName();

    String getType();

    String getLocation();

    String getAddress1();

    String getAddress2();

    String getAddress3();

    String getAddress4();

    String getCity();

    String getMobileNo();

    String getPhoneNo();

    String getFax();

    String getEmail();

    String getSignAuth();

    String getTaxApplicable();

    String getTaxType();

    String getCentralPoolAcFlag();

    String getOuPremesisStatus();

    String getLeaseStartDatetime();

    String getLeaseEndDatetime();

    String getGatewayFlag();

    double getDepositAmount();

    String getStatus();

    String getRemarks();

    String getDirectIndirectOu();

    String getWeeklyOffDatetime();

    String getMetroMultiFlag();

    String getMetroApplicableFlag();

    String getMetroOu();

    String getOperationalStatus();

    Date getStartDatetime();

    Date getEndDatetime();

    String getOuHierarchyType();

    String getAcClosedStatus();

    Date getAcClosedDatetime();

    String getOuOnlineFlag();

    String getUploadedData();

    String getLocId();

    String getZoneOuCode();

    String getCallCenterOuOode();

    String getTerritoryOuCode();

    String getGatiJubileeFlag();

    String getSbuCode();

    String getAreaCode();

}
