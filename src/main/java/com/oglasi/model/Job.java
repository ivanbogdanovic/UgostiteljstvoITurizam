package com.oglasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "jobs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findByJobId", query = "SELECT j FROM Job j WHERE j.jobId = :jobId"),
    @NamedQuery(name = "Job.findByTitle", query = "SELECT j FROM Job j WHERE j.title = :title"),
    @NamedQuery(name = "Job.findByContactPerson", query = "SELECT j FROM Job j WHERE j.contactPerson = :contactPerson"),
    @NamedQuery(name = "Job.findByPhone", query = "SELECT j FROM Job j WHERE j.phone = :phone"),
    @NamedQuery(name = "Job.findByEmail", query = "SELECT j FROM Job j WHERE j.email = :email"),
    @NamedQuery(name = "Job.findByActiveDate", query = "SELECT j FROM Job j WHERE j.activeDate = :activeDate"),
    @NamedQuery(name = "Job.findByDuration", query = "SELECT j FROM Job j WHERE j.duration = :duration"),
    @NamedQuery(name = "Job.findByStatus", query = "SELECT j FROM Job j WHERE j.status = :status"),
    @NamedQuery(name = "Job.findByCreateDate", query = "SELECT j FROM Job j WHERE j.createDate = :createDate"),
    @NamedQuery(name = "Job.findByCompanyAndStatus", query = "SELECT j FROM Job j WHERE j.companyId = :companyId AND j.status = :status")})
public class Job implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private short duration;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "job_id")
    private Integer jobId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "contact_person")
    private String contactPerson;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "active_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne(optional = false)
    private City cityId;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private Company companyId;
    @JoinColumn(name = "employment_type_id", referencedColumnName = "employment_type_id")
    @ManyToOne(optional = false)
    private EmploymentType employmentTypeId;
    @JoinColumn(name = "job_position_id", referencedColumnName = "job_position_id")
    @ManyToOne(optional = false)
    private JobPosition jobPositionId;

    public Job() {
    }

    public Job(Integer jobId) {
        this.jobId = jobId;
    }
    
    public Job(Integer jobId, String title, String description, Date activeDate, short status, Date createDate) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.activeDate = activeDate;
        this.status = status;
        this.createDate = createDate;
    }

    
    public Job(String title, City cityId, JobPosition jobPositionId, EmploymentType employmentTypeId, Company companyId, String address, String description, String contactPerson, String phone, String email, short status, Date createDate, Short duration){
        this.title = title;
        this.cityId = cityId;
        this.jobPositionId = jobPositionId;
        this.employmentTypeId = employmentTypeId;
        this.companyId = companyId;
        this.address = address;
        this.description = description;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.createDate = createDate;
        this.duration = duration;
    }
    
    
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }
    
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public EmploymentType getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(EmploymentType employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public JobPosition getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(JobPosition jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oglasi.model.Job[ jobId=" + jobId + " ]";
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

}
