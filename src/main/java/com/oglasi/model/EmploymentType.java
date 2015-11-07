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
@Table(name = "employment_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmploymentType.findAll", query = "SELECT e FROM EmploymentType e"),
    @NamedQuery(name = "EmploymentType.findByEmploymentTypeId", query = "SELECT e FROM EmploymentType e WHERE e.employmentTypeId = :employmentTypeId"),
    @NamedQuery(name = "EmploymentType.findByTypeName", query = "SELECT e FROM EmploymentType e WHERE e.typeName = :typeName")})
public class EmploymentType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employment_type_id")
    private Integer employmentTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type_name")
    private String typeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employmentTypeId")
    private Collection<Job> jobCollection;

    public EmploymentType() {
    }

    public EmploymentType(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public EmploymentType(Integer employmentTypeId, String typeName) {
        this.employmentTypeId = employmentTypeId;
        this.typeName = typeName;
    }

    public Integer getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Integer employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employmentTypeId != null ? employmentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploymentType)) {
            return false;
        }
        EmploymentType other = (EmploymentType) object;
        if ((this.employmentTypeId == null && other.employmentTypeId != null) || (this.employmentTypeId != null && !this.employmentTypeId.equals(other.employmentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oglasi.model.EmploymentType[ employmentTypeId=" + employmentTypeId + " ]";
    }

}
