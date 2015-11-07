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
@Table(name = "job_positions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobPosition.findAll", query = "SELECT j FROM JobPosition j"),
    @NamedQuery(name = "JobPosition.findByJobPositionId", query = "SELECT j FROM JobPosition j WHERE j.jobPositionId = :jobPositionId"),
    @NamedQuery(name = "JobPosition.findByPositionName", query = "SELECT j FROM JobPosition j WHERE j.positionName = :positionName")})
public class JobPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "job_position_id")
    private Integer jobPositionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "position_name")
    private String positionName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobPositionId")
    private Collection<Job> jobCollection;

    public JobPosition() {
    }

    public JobPosition(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public JobPosition(Integer jobPositionId, String positionName) {
        this.jobPositionId = jobPositionId;
        this.positionName = positionName;
    }

    public Integer getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(Integer jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
        hash += (jobPositionId != null ? jobPositionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobPosition)) {
            return false;
        }
        JobPosition other = (JobPosition) object;
        if ((this.jobPositionId == null && other.jobPositionId != null) || (this.jobPositionId != null && !this.jobPositionId.equals(other.jobPositionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oglasi.model.JobPosition[ jobPositionId=" + jobPositionId + " ]";
    }

}
