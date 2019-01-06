package com.gati.mdm.oumaster.common.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.javers.core.metamodel.annotation.DiffIgnore;

@Entity
@Table(name = "ou_master")
@DynamicUpdate(value = true)
@IdClass(PrimaryKeyOuCodeCompanyId.class)
public class OuMaster implements Serializable {
    private static final long serialVersionUID = 3690075465593764272L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @DiffIgnore
    private Long id;

    @Id
    @NotBlank
    @Size(min = 3, max = 5, message = "OuCode '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[A-Z\\s]+$", message = "Only Alphabets are allowed")
    @Column(name = "ou_code", length = 5, unique = true)
    private String ouCode;

    @Id
    @Min(value = 1, message = "Company code should be a positive integer and at least {value}")
    @Column(name = "company_id", unique = true)
    private Integer companyId;

    @Size(min = 2, max = 2, message = "StateCode '${validatedValue}' must be between {min} and {max} characters long")
    @NotBlank
    @Column(name = "state_code", length = 5)
    private String stateCode;

    @Size(min = 1, max = 2, message = "OperationalStatus '${validatedValue}' must be between {min} and {max} characters long")
    @NotBlank
    @Column(name = "operational_status", length = 2)
    private String operationalStatus;

    @Size(min = 1, max = 2, message = "Ou AcClosedStatus '${validatedValue}' must be between {min} and {max} characters long")
    @NotBlank
    @Column(name = "ac_closed_status", length = 2)
    private String acClosedStatus;

    @Size(min = 1, max = 2, message = "Ou OnlineFlag '${validatedValue}' must be between {min} and {max} characters long")
    @NotBlank
    @Column(name = "ou_online_flag", length = 2)
    private String ouOnlineFlag;

    @Size(min = 1, max = 2, message = "GatiJubileeFlag '${validatedValue}' must be between {min} and {max} characters long")
    @NotBlank
    @Column(name = "gati_jubilee_flag", length = 2)
    private String gatiJubileeFlag;

    @Size(min = 2, max = 5, message = "CurrencyCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "currency_code", length = 5)
    private String currencyCode;

    @Size(min = 3, max = 5, message = "ReportingOu '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[A-Z\\s]+$", message = "Only Alphabets are allowed")
    @Column(name = "reporting_ou", length = 5)
    private String reportingOu;

    @Size(min = 1, max = 5, message = "OuCategoryCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "ou_category_code", length = 5)
    private String ouCategoryCode;

    @Size(min = 3, max = 5, message = "AccountingOu '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[A-Z\\s]+$", message = "Only Alphabets are allowed")
    @Column(name = "accounting_ou", length = 5)
    private String accountingOu;

    @Size(min = 6, max = 10, message = "PinCode '${validatedValue}' must be between {min} and {max} characters long")
    @Pattern(regexp = "^[1-9][0-9\\s]{5}$", message = "PinCode should have 6 digits")
    @Column(name = "pincode", length = 10)
    private String pincode;

    @Size(min = 2, max = 3, message = "CountryCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Size(min = 3, max = 10, message = "StaffCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "staff_code", length = 10)
    private String staffCode;

    @Size(min = 3, max = 50, message = "OuName '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 2, message = "OuType '${validatedValue}' must be {max} characters long")
    @Column(name = "type", length = 2)
    private String type;

    @Size(min = 3, max = 20, message = "Location '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "location", length = 20)
    private String location;

    @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "address1", length = 50)
    private String address1;

    @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "address2", length = 50)
    private String address2;

    @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "address3", length = 50)
    private String address3;

    @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "address4", length = 50)
    private String address4;

    @Size(min = 2, max = 20, message = "City '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "city", length = 20)
    private String city;

    @Size(min = 6, max = 50, message = "MobileNo '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "mobile_no", length = 50)
    private String mobileNo;

    @Size(min = 6, max = 50, message = "PhoneNo '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "phone_no", length = 50)
    private String phoneNo;

    @Size(min = 4, max = 15, message = "Fax '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "fax", length = 15)
    private String fax;

    @Email
    @Column(name = "email", length = 50)
    private String email;

    @Size(min = 1, max = 30, message = "SignAuth '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "sign_auth", length = 30)
    private String signAuth;

    @Size(min = 1, max = 2, message = "TaxApplicable '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "tax_applicable", length = 2)
    private String taxApplicable;

    @Size(min = 1, max = 25, message = "TaxType '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "tax_type", length = 25)
    private String taxType;

    @Size(min = 1, max = 2, message = "CentralPoolAcFlag '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "central_pool_ac_flag", length = 2)
    private String centralPoolAcFlag;

    @Size(min = 1, max = 2, message = "OuPremesisStatus '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "ou_premesis_status", length = 2)
    private String ouPremesisStatus;

    @Column(name = "lease_start_datetime")
    private LocalDateTime leaseStartDatetime;

    @Column(name = "lease_end_datetime")
    private LocalDateTime leaseEndDatetime;

    @Size(min = 1, max = 2, message = "GatewayFlag '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "gateway_flag", length = 2)
    private String gatewayFlag;

    @DecimalMin("0.0")
    @Column(name = "deposit_amount", length = 12, precision = 2)
    private double depositAmount;

    @Size(min = 1, max = 2, message = "OU Status '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "status", length = 2)
    private String status;

    @Size(min = 5, max = 200, message = "Remarks '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "remarks", length = 200)
    private String remarks;

    @Column(name = "created_by", length = 20)
    @DiffIgnore
    private String createdBy;

    @Column(name = "created_datetime", nullable = false, updatable = false)
    @CreationTimestamp
    @DiffIgnore
    private LocalDateTime createdDatetime;

    @Column(name = "last_updated_by", length = 20)
    @DiffIgnore
    private String lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = "last_updated_datetime", nullable = false)
    @DiffIgnore
    private LocalDateTime lastUpdatedDatetime;

    @Size(min = 1, max = 5, message = "DirectIndirectOu '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "direct_indirect_ou", length = 5)
    private String directIndirectOu;

    @Size(min = 1, max = 10, message = "WeeklyOffDatetime '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "weekly_off_datetime", length = 10)
    private String weeklyOffDatetime;

    @Size(min = 1, max = 2, message = "MetroMultiFlag '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "metro_multi_flag", length = 2)
    private String metroMultiFlag;

    @Size(min = 1, max = 10, message = "MetroApplicableFlag '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "metro_applicable_flag", length = 10)
    private String metroApplicableFlag;

    @Size(min = 1, max = 5, message = "MetroOu '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "metro_ou", length = 5)
    private String metroOu;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @Column(name = "end_datetime")
    private LocalDateTime endDatetime;

    @Size(min = 1, max = 2, message = "OuHierarchyType '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "ou_hierarchy_type", length = 2)
    private String ouHierarchyType;

    // @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ac_closed_datetime")
    private LocalDateTime acClosedDatetime;

    @Size(max = 1, message = "UploadedData '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "uploaded_data", length = 1)
    @DiffIgnore
    private String uploadedData;

    @Size(min = 1, max = 50, message = "Location Id '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "loc_id", length = 50)
    private String locId;

    @Size(min = 1, max = 5, message = "ZoneOuCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "zone_ou_code", length = 5)
    private String zoneOuCode;

    @Size(min = 1, max = 5, message = "CallCenterOuCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "call_center_ou_code", length = 5)
    private String callCenterOuCode;

    @Size(min = 1, max = 5, message = "TerritoryOuCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "territory_ou_code", length = 5)
    private String territoryOuCode;

    @Size(min = 1, max = 5, message = "SbuCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "sbu_code", length = 5)
    private String sbuCode;

    @Size(min = 1, max = 5, message = "AreaCode '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "area_code", length = 5)
    private String areaCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getReportingOu() {
        return reportingOu;
    }

    public void setReportingOu(String reportingOu) {
        this.reportingOu = reportingOu;
    }

    public String getOuCategoryCode() {
        return ouCategoryCode;
    }

    public void setOuCategoryCode(String ouCategoryCode) {
        this.ouCategoryCode = ouCategoryCode;
    }

    public String getAccountingOu() {
        return accountingOu;
    }

    public void setAccountingOu(String accountingOu) {
        this.accountingOu = accountingOu;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignAuth() {
        return signAuth;
    }

    public void setSignAuth(String signAuth) {
        this.signAuth = signAuth;
    }

    public String getTaxApplicable() {
        return taxApplicable;
    }

    public void setTaxApplicable(String taxApplicable) {
        this.taxApplicable = taxApplicable;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getCentralPoolAcFlag() {
        return centralPoolAcFlag;
    }

    public void setCentralPoolAcFlag(String centralPoolAcFlag) {
        this.centralPoolAcFlag = centralPoolAcFlag;
    }

    public String getOuPremesisStatus() {
        return ouPremesisStatus;
    }

    public void setOuPremesisStatus(String ouPremesisStatus) {
        this.ouPremesisStatus = ouPremesisStatus;
    }

    public LocalDateTime getLeaseStartDatetime() {
        return leaseStartDatetime;
    }

    public void setLeaseStartDatetime(LocalDateTime leaseStartDatetime) {
        this.leaseStartDatetime = leaseStartDatetime;
    }

    public LocalDateTime getLeaseEndDatetime() {
        return leaseEndDatetime;
    }

    public void setLeaseEndDatetime(LocalDateTime leaseEndDatetime) {
        this.leaseEndDatetime = leaseEndDatetime;
    }

    public String getGatewayFlag() {
        return gatewayFlag;
    }

    public void setGatewayFlag(String gatewayFlag) {
        this.gatewayFlag = gatewayFlag;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedDatetime() {
        return lastUpdatedDatetime;
    }

    public void setLastUpdatedDatetime(LocalDateTime lastUpdatedDatetime) {
        this.lastUpdatedDatetime = lastUpdatedDatetime;
    }

    public String getDirectIndirectOu() {
        return directIndirectOu;
    }

    public void setDirectIndirectOu(String directIndirectOu) {
        this.directIndirectOu = directIndirectOu;
    }

    public String getWeeklyOffDatetime() {
        return weeklyOffDatetime;
    }

    public void setWeeklyOffDatetime(String weeklyOffDatetime) {
        this.weeklyOffDatetime = weeklyOffDatetime;
    }

    public String getMetroMultiFlag() {
        return metroMultiFlag;
    }

    public void setMetroMultiFlag(String metroMultiFlag) {
        this.metroMultiFlag = metroMultiFlag;
    }

    public String getMetroApplicableFlag() {
        return metroApplicableFlag;
    }

    public void setMetroApplicableFlag(String metroApplicableFlag) {
        this.metroApplicableFlag = metroApplicableFlag;
    }

    public String getMetroOu() {
        return metroOu;
    }

    public void setMetroOu(String metroOu) {
        this.metroOu = metroOu;
    }

    public String getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getOuHierarchyType() {
        return ouHierarchyType;
    }

    public void setOuHierarchyType(String ouHierarchyType) {
        this.ouHierarchyType = ouHierarchyType;
    }

    public String getAcClosedStatus() {
        return acClosedStatus;
    }

    public void setAcClosedStatus(String acClosedStatus) {
        this.acClosedStatus = acClosedStatus;
    }

    public LocalDateTime getAcClosedDatetime() {
        return acClosedDatetime;
    }

    public void setAcClosedDatetime(LocalDateTime acClosedDatetime) {
        this.acClosedDatetime = acClosedDatetime;
    }

    public String getOuOnlineFlag() {
        return ouOnlineFlag;
    }

    public void setOuOnlineFlag(String ouOnlineFlag) {
        this.ouOnlineFlag = ouOnlineFlag;
    }

    public String getUploadedData() {
        return uploadedData;
    }

    public void setUploadedData(String uploadedData) {
        this.uploadedData = uploadedData;
    }

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public String getZoneOuCode() {
        return zoneOuCode;
    }

    public void setZoneOuCode(String zoneOuCode) {
        this.zoneOuCode = zoneOuCode;
    }

    public String getcallCenterOuCode() {
        return callCenterOuCode;
    }

    public void setcallCenterOuCode(String callCenterOuCode) {
        this.callCenterOuCode = callCenterOuCode;
    }

    public String getTerritoryOuCode() {
        return territoryOuCode;
    }

    public void setTerritoryOuCode(String territoryOuCode) {
        this.territoryOuCode = territoryOuCode;
    }

    public String getGatiJubileeFlag() {
        return gatiJubileeFlag;
    }

    public void setGatiJubileeFlag(String gatiJubileeFlag) {
        this.gatiJubileeFlag = gatiJubileeFlag;
    }

    public String getSbuCode() {
        return sbuCode;
    }

    public void setSbuCode(String sbuCode) {
        this.sbuCode = sbuCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public OuMaster(Long id,
            @NotBlank
            @Size(min = 3, max = 5, message = "OuCode '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[A-Z\\s]+$",
            message = "Only Alphabets are allowed") String ouCode,
            @Min(value = 1, message = "Company code should be a positive integer and at least {value}") Integer companyId,
            @Size(min = 2, max = 2, message = "StateCode '${validatedValue}' must be between {min} and {max} characters long")
            @NotBlank String stateCode,
            @Size(min = 1, max = 2, message = "OperationalStatus '${validatedValue}' must be between {min} and {max} characters long")
            @NotBlank String operationalStatus,
            @Size(min = 1, max = 2, message = "Ou AcClosedStatus '${validatedValue}' must be between {min} and {max} characters long")
            @NotBlank String acClosedStatus,
            @Size(min = 1, max = 2, message = "Ou OnlineFlag '${validatedValue}' must be between {min} and {max} characters long")
            @NotBlank String ouOnlineFlag,
            @Size(min = 1, max = 2, message = "GatiJubileeFlag '${validatedValue}' must be between {min} and {max} characters long")
            @NotBlank String gatiJubileeFlag,
            @Size(min = 2, max = 5, message = "CurrencyCode '${validatedValue}' must be between {min} and {max} characters long") String currencyCode,
            @Size(min = 3, max = 5, message = "ReportingOu '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[A-Z\\s]+$",
            message = "Only Alphabets are allowed") String reportingOu,
            @Size(min = 1, max = 5, message = "OuCategoryCode '${validatedValue}' must be between {min} and {max} characters long") String ouCategoryCode,
            @Size(min = 3, max = 5, message = "AccountingOu '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[A-Z\\s]+$",
            message = "Only Alphabets are allowed") String accountingOu,
            @Size(min = 6, max = 10, message = "PinCode '${validatedValue}' must be between {min} and {max} characters long")
            @Pattern(regexp = "^[1-9][0-9\\s]{5}$",
            message = "PinCode should have 6 digits") String pincode,
            @Size(min = 2, max = 3, message = "CountryCode '${validatedValue}' must be between {min} and {max} characters long") String countryCode,
            @Size(min = 3, max = 10, message = "StaffCode '${validatedValue}' must be between {min} and {max} characters long") String staffCode,
            @Size(min = 3, max = 50, message = "OuName '${validatedValue}' must be between {min} and {max} characters long") String name,
            @Size(max = 2, message = "OuType '${validatedValue}' must be {max} characters long") String type,
            @Size(min = 3, max = 20, message = "Location '${validatedValue}' must be between {min} and {max} characters long") String location,
            @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long") String address1,
            @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long") String address2,
            @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long") String address3,
            @Size(min = 1, max = 50, message = "Address Item '${validatedValue}' must be between {min} and {max} characters long") String address4,
            @Size(min = 2, max = 20, message = "City '${validatedValue}' must be between {min} and {max} characters long") String city,
            @Size(min = 6, max = 50, message = "MobileNo '${validatedValue}' must be between {min} and {max} characters long") String mobileNo,
            @Size(min = 6, max = 50, message = "PhoneNo '${validatedValue}' must be between {min} and {max} characters long") String phoneNo,
            @Size(min = 4, max = 15, message = "Fax '${validatedValue}' must be between {min} and {max} characters long") String fax,
            @Email String email,
            @Size(min = 1, max = 30, message = "SignAuth '${validatedValue}' must be between {min} and {max} characters long") String signAuth,
            @Size(min = 1, max = 2, message = "TaxApplicable '${validatedValue}' must be between {min} and {max} characters long") String taxApplicable,
            @Size(min = 1, max = 25, message = "TaxType '${validatedValue}' must be between {min} and {max} characters long") String taxType,
            @Size(min = 1, max = 2, message = "CentralPoolAcFlag '${validatedValue}' must be between {min} and {max} characters long") String centralPoolAcFlag,
            @Size(min = 1, max = 2, message = "OuPremesisStatus '${validatedValue}' must be between {min} and {max} characters long") String ouPremesisStatus,
            LocalDateTime leaseStartDatetime, LocalDateTime leaseEndDatetime,
            @Size(min = 1, max = 2, message = "GatewayFlag '${validatedValue}' must be between {min} and {max} characters long") String gatewayFlag,
            @DecimalMin("0.0") double depositAmount,
            @Size(min = 1, max = 2, message = "OU Status '${validatedValue}' must be between {min} and {max} characters long") String status,
            @Size(min = 5, max = 200, message = "Remarks '${validatedValue}' must be between {min} and {max} characters long") String remarks,
            String createdBy, LocalDateTime createdDatetime, String lastUpdatedBy, LocalDateTime lastUpdatedDatetime,
            @Size(min = 1, max = 5, message = "DirectIndirectOu '${validatedValue}' must be between {min} and {max} characters long") String directIndirectOu,
            @Size(min = 1, max = 10, message = "WeeklyOffDatetime '${validatedValue}' must be between {min} and {max} characters long") String weeklyOffDatetime,
            @Size(min = 1, max = 2, message = "MetroMultiFlag '${validatedValue}' must be between {min} and {max} characters long") String metroMultiFlag,
            @Size(min = 1, max = 10, message = "MetroApplicableFlag '${validatedValue}' must be between {min} and {max} characters long") String metroApplicableFlag,
            @Size(min = 1, max = 5, message = "MetroOu '${validatedValue}' must be between {min} and {max} characters long") String metroOu,
            LocalDateTime startDatetime, LocalDateTime endDatetime,
            @Size(min = 1, max = 2, message = "OuHierarchyType '${validatedValue}' must be between {min} and {max} characters long") String ouHierarchyType,
            LocalDateTime acClosedDatetime,
            @Size(max = 1, message = "UploadedData '${validatedValue}' must be between {min} and {max} characters long") String uploadedData,
            @Size(min = 1, max = 50, message = "Location Id '${validatedValue}' must be between {min} and {max} characters long") String locId,
            @Size(min = 1, max = 5, message = "ZoneOuCode '${validatedValue}' must be between {min} and {max} characters long") String zoneOuCode,
            @Size(min = 1, max = 5, message = "CallCenterOuCode '${validatedValue}' must be between {min} and {max} characters long") String callCenterOuCode,
            @Size(min = 1, max = 5, message = "TerritoryOuCode '${validatedValue}' must be between {min} and {max} characters long") String territoryOuCode,
            @Size(min = 1, max = 5, message = "SbuCode '${validatedValue}' must be between {min} and {max} characters long") String sbuCode,
            @Size(min = 1, max = 5, message = "AreaCode '${validatedValue}' must be between {min} and {max} characters long") String areaCode) {
        super();
        this.id = id;
        this.ouCode = ouCode;
        this.companyId = companyId;
        this.stateCode = stateCode;
        this.operationalStatus = operationalStatus;
        this.acClosedStatus = acClosedStatus;
        this.ouOnlineFlag = ouOnlineFlag;
        this.gatiJubileeFlag = gatiJubileeFlag;
        this.currencyCode = currencyCode;
        this.reportingOu = reportingOu;
        this.ouCategoryCode = ouCategoryCode;
        this.accountingOu = accountingOu;
        this.pincode = pincode;
        this.countryCode = countryCode;
        this.staffCode = staffCode;
        this.name = name;
        this.type = type;
        this.location = location;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.address4 = address4;
        this.city = city;
        this.mobileNo = mobileNo;
        this.phoneNo = phoneNo;
        this.fax = fax;
        this.email = email;
        this.signAuth = signAuth;
        this.taxApplicable = taxApplicable;
        this.taxType = taxType;
        this.centralPoolAcFlag = centralPoolAcFlag;
        this.ouPremesisStatus = ouPremesisStatus;
        this.leaseStartDatetime = leaseStartDatetime;
        this.leaseEndDatetime = leaseEndDatetime;
        this.gatewayFlag = gatewayFlag;
        this.depositAmount = depositAmount;
        this.status = status;
        this.remarks = remarks;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDatetime = lastUpdatedDatetime;
        this.directIndirectOu = directIndirectOu;
        this.weeklyOffDatetime = weeklyOffDatetime;
        this.metroMultiFlag = metroMultiFlag;
        this.metroApplicableFlag = metroApplicableFlag;
        this.metroOu = metroOu;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.ouHierarchyType = ouHierarchyType;
        this.acClosedDatetime = acClosedDatetime;
        this.uploadedData = uploadedData;
        this.locId = locId;
        this.zoneOuCode = zoneOuCode;
        this.callCenterOuCode = callCenterOuCode;
        this.territoryOuCode = territoryOuCode;
        this.sbuCode = sbuCode;
        this.areaCode = areaCode;
    }

    @Override
    public String toString() {
        return "OuMaster [id=" + id + ", ouCode=" + ouCode + ", companyId=" + companyId + ", stateCode=" + stateCode
                + ", currencyCode=" + currencyCode + ", reportingOu=" + reportingOu + ", ouCategoryCode="
                + ouCategoryCode + ", accountingOu=" + accountingOu + ", pincode=" + pincode + ", countryCode="
                + countryCode + ", staffCode=" + staffCode + ", name=" + name + ", type=" + type + ", location="
                + location + ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3
                + ", address4=" + address4 + ", city=" + city + ", mobileNo=" + mobileNo + ", phoneNo=" + phoneNo
                + ", fax=" + fax + ", email=" + email + ", signAuth=" + signAuth + ", taxApplicable=" + taxApplicable
                + ", taxType=" + taxType + ", centralPoolAcFlag=" + centralPoolAcFlag + ", ouPremesisStatus="
                + ouPremesisStatus + ", leaseStartDatetime=" + leaseStartDatetime + ", leaseEndDatetime="
                + leaseEndDatetime + ", gatewayFlag=" + gatewayFlag + ", depositAmount=" + depositAmount + ", status="
                + status + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDatetime=" + createdDatetime
                + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDatetime=" + lastUpdatedDatetime
                + ", directIndirectOu=" + directIndirectOu + ", weeklyOffDatetime=" + weeklyOffDatetime
                + ", metroMultiFlag=" + metroMultiFlag + ", metroApplicableFlag=" + metroApplicableFlag + ", metroOu="
                + metroOu + ", operationalStatus=" + operationalStatus + ", startDatetime=" + startDatetime
                + ", endDatetime=" + endDatetime + ", ouHierarchyType=" + ouHierarchyType + ", acClosedStatus="
                + acClosedStatus + ", acClosedDatetime=" + acClosedDatetime + ", ouOnlineFlag=" + ouOnlineFlag
                + ", uploadedData=" + uploadedData + ", locId=" + locId + ", zoneOuCode=" + zoneOuCode
                + ", callCenterOuCode=" + callCenterOuCode + ", territoryOuCode=" + territoryOuCode
                + ", gatiJubileeFlag=" + gatiJubileeFlag + ", sbuCode=" + sbuCode + ", areaCode=" + areaCode + "]";
    }

    public OuMaster() {
        super();
    }

}
