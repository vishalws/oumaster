package com.gati.mdm.oumaster.common.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.immutables.value.Value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Value.Immutable
public interface OuMasterListRequest {

    @ApiModelProperty(value = "Ou Code", example = "HYDN")
    @Size(min = 2, max = 5, message = "The OuCode '${validatedValue}' must be between {min} and {max} characters long")
    public String getOuCode();

    @ApiModelProperty(value = "Company Code", example = "101")
    @Min(value = 100, message = "The Company code should be at least {value}")
    public Integer getCompanyId();

    @ApiModelProperty(value = "Reporting OuCode", example = "HYDZ")
    @Size(min = 2, max = 5, message = "The Reporting Ou '${validatedValue}' must be between {min} and {max} characters long")
    public String getReportingOu();

    @ApiModelProperty(value = "Ou CategoryCode", example = "")
    @Size(min = 2, max = 5, message = "The OuCategoryCode '${validatedValue}' must be between {min} and {max} characters long")
    public String getOuCategoryCode();

    @ApiModelProperty(value = "Reporting OuCode", example = "HYDZ")
    @Size(min = 2, max = 5, message = "The Accounting Ou '${validatedValue}' must be between {min} and {max} characters long")
    public String getAccountingOu();

    @ApiModelProperty(value = "PinCode", example = "522006")
    @Size(min = 3, max = 6, message = "The PinCode '${validatedValue}' must be between {min} and {max} characters long")
    public String getPincode();

    @ApiModelProperty(value = "Ou Name", example = "")
    @Size(min = 3, max = 50, message = "The OuName '${validatedValue}' must be between {min} and {max} characters long")
    public String getName();

    @ApiModelProperty(value = "Ou Type", example = "")
    @Size(min = 1, max = 2, message = "The OuType '${validatedValue}' must be between {min} and {max} characters long")
    public String getType();

    @ApiModelProperty(value = "City", example = "Hyderabad")
    @Size(min = 2, max = 20, message = "The City '${validatedValue}' must be between {min} and {max} characters long")
    public String getCity();

    @ApiModelProperty(value = "Ou Status", example = "")
    @Size(min = 1, max = 2, message = "The OuStatus '${validatedValue}' must be between {min} and {max} characters long")
    public String getStatus();

    @ApiModelProperty(value = "Ou Operational Status", example = "")
    @Size(min = 1, max = 2, message = "The OuOperationalStatus '${validatedValue}' must be between {min} and {max} characters long")
    public String getOperationalStatus();

    @ApiModelProperty(value = "Ou Hierarchy Type", example = "")
    @Size(min = 1, max = 2, message = "The OuHierarchyType '${validatedValue}' must be between {min} and {max} characters long")
    public String getOuHierarchyType();

    @ApiModelProperty(value = "Ou Account Closed Status", example = "")
    @Size(min = 1, max = 2, message = "The OuAcClosedStatus '${validatedValue}' must be between {min} and {max} characters long")
    public String getAcClosedStatus();

    @ApiModelProperty(value = "GatiJubilee Flag", example = "")
    @Size(min = 1, max = 2, message = "The GatiJubileeFlag '${validatedValue}' must be between {min} and {max} characters long")
    public String getGatiJubileeFlag();

    @ApiModelProperty(value = "Sbu Code", example = "")
    @Size(min = 1, max = 5, message = "The SbuCode '${validatedValue}' must be between {min} and {max} characters long")
    public String getSbuCode();

}
