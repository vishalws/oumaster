package com.gati.mdm.oumaster.common.data.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class PrimaryKeyOuCodeCompanyId implements Serializable {

    private static final long serialVersionUID = 4550864929619373094L;

    private Long id;

    @Column(name = "ou_code", length = 5)

    private String ouCode;

    @Column(name = "company_id")
    private Integer companyId;

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

    public PrimaryKeyOuCodeCompanyId(String ouCode, Integer companyId) {
        super();

        this.ouCode = ouCode;
        this.companyId = companyId;
    }

    public PrimaryKeyOuCodeCompanyId() {
        super();

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PrimaryKeyOuCodeCompanyId that = (PrimaryKeyOuCodeCompanyId) object;

        if (!ouCode.equals(that.ouCode)) {
            return false;
        }
        return companyId.equals(that.companyId);
    }

    @Override
    public int hashCode() {
        int result = ouCode.hashCode();
        result = 31 * result + companyId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PrimaryKeyOuCodeCompanyId [ ouCode=" + ouCode + ", companyId=" + companyId + "]";
    }

}
