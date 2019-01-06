package com.gati.mdm.oumaster.common.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.immutables.value.Value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Value.Immutable
@Value.Style(allParameters = true)
public interface DeactivateOuMasterDto {

    @ApiModelProperty(value = "Ou Code", example = "HYDN")
    @NotNull
    public String getOuCode();

    @ApiModelProperty(value = "Company Code", example = "101")
    @NotNull
    public Integer getCompanyId();

    public String getStateCode();

    public String getCurrencyCode();

    public String getReportingOu();

    public String getOuCategoryCode();

    public String getAccountingOu();

    public String getPincode();

    public String getCountryCode();

    public String getStaffCode();

    public String getName();

    public String getType();

    public String getLocation();

    public String getAddress1();

    public String getAddress2();

    public String getAddress3();

    public String getAddress4();

    public String getCity();

    public String getMobileNo();

    public String getPhoneNo();

    public String getFax();

    @ApiModelProperty(position = 1, value = "OU's Email", example = "peter.blair@test.com")
    @Email
    public String getEmail();

    public String getSignAuth();

    public String getTaxApplicable();

    public String getTaxType();

    public String getCentralPoolAcFlag();

    public String getOuPremesisStatus();

    public String getLeaseStartDatetime();

    public String getLeaseEndDatetime();

    public String getGatewayFlag();

    public double getDepositAmount();

    public String getStatus();

    public String getRemarks();

    public String getDirectIndirectOu();

    public String getWeeklyOffDatetime();

    public String getMetroMultiFlag();

    public String getMetroApplicableFlag();

    public String getMetroOu();

    public String getOperationalStatus();

    public Date getStartDatetime();

    public Date getEndDatetime();

    public String getOuHierarchyType();

    public String getAcClosedStatus();

    public Date getAcClosedDatetime();

    public String getOuOnlineFlag();

    public String getUploadedData();

    public String getLocId();

    public String getZoneOuCode();

    public String getCallCenterOuOode();

    public String getTerritoryOuCode();

    public String getGatiJubileeFlag();

    public String getSbuCode();

    public String getAreaCode();

}
