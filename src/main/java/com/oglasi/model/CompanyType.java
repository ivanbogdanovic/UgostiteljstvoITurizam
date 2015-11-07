package com.oglasi.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "company_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyType.findAll", query = "SELECT c FROM CompanyType c"),
    @NamedQuery(name = "CompanyType.findByCompanyTypeId", query = "SELECT c FROM CompanyType c WHERE c.companyTypeId = :companyTypeId"),
    @NamedQuery(name = "CompanyType.findByTypeName", query = "SELECT c FROM CompanyType c WHERE c.typeName = :typeName")})
public class CompanyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_type_id")
    private Short companyTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyTypeId")
    private Collection<Company> companyCollection;

    public CompanyType() {
    }

    public CompanyType(Short companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public CompanyType(Short companyTypeId, String typeName) {
        this.companyTypeId = companyTypeId;
        this.typeName = typeName;
    }

    public Short getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Short companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<Company> getCompanyCollection() {
        return companyCollection;
    }

    public void setCompanyCollection(Collection<Company> companyCollection) {
        this.companyCollection = companyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyTypeId != null ? companyTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyType)) {
            return false;
        }
        CompanyType other = (CompanyType) object;
        if ((this.companyTypeId == null && other.companyTypeId != null) || (this.companyTypeId != null && !this.companyTypeId.equals(other.companyTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oglasi.model.CompanyType[ companyTypeId=" + companyTypeId + " ]";
    }

}
