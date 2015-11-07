package com.oglasi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "companies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyId", query = "SELECT c FROM Company c WHERE c.companyId = :companyId"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByEmail", query = "SELECT c FROM Company c WHERE c.email = :email"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone"),
    @NamedQuery(name = "Company.findByFax", query = "SELECT c FROM Company c WHERE c.fax = :fax"),
    @NamedQuery(name = "Company.findByWeb", query = "SELECT c FROM Company c WHERE c.web = :web"),
    @NamedQuery(name = "Company.findByPib", query = "SELECT c FROM Company c WHERE c.pib = :pib"),
    @NamedQuery(name = "Company.findByMaticniBroj", query = "SELECT c FROM Company c WHERE c.maticniBroj = :maticniBroj"),
    @NamedQuery(name = "Company.findByLogo", query = "SELECT c FROM Company c WHERE c.logo = :logo"),
    @NamedQuery(name = "Company.findByPassword", query = "SELECT c FROM Company c WHERE c.password = :password"),
    @NamedQuery(name = "Company.findByCreateDate", query = "SELECT c FROM Company c WHERE c.createDate = :createDate"),
    @NamedQuery(name = "Company.findByLastUpdate", query = "SELECT c FROM Company c WHERE c.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Company.findByStatus", query = "SELECT c FROM Company c WHERE c.status = :status"),
    @NamedQuery(name = "Company.findByLogin", query = "SELECT c FROM Company c WHERE c.email = :email AND c.password = :password")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_id")
    private Short companyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "fax")
    private String fax;
    @Size(max = 45)
    @Column(name = "web")
    private String web;
    @Size(max = 15)
    @Column(name = "pib")
    private String pib;
    @Size(max = 15)
    @Column(name = "maticni_broj")
    private String maticniBroj;
    @Size(max = 255)
    @Column(name = "logo")
    private String logo;
    @Lob
    @Size(max = 65535)
    @Column(name = "about")
    private String about;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private short status;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne(optional = false)
    private City cityId;
    @JoinColumn(name = "company_type_id", referencedColumnName = "company_type_id")
    @ManyToOne(optional = false)
    private CompanyType companyTypeId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "companyId")
    @Cascade(CascadeType.ALL)
    private Collection<Job> jobCollection;

    public Company() {
    }

    public Company(Short companyId) {
        this.companyId = companyId;
    }

    public Company(Short companyId, String name, String address, String email, String password, Date createDate, Date lastUpdate, short status) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }
    
     public Company(String name, String address, String web, String email, String phone, String fax, String maticniBroj, String pib, String password, String about, String logo, Date lastUpdate, Date createDate,City cityId, CompanyType companyTypeId, short status) {
        this.name = name;
        this.address = address;
        this.cityId = cityId;
        this.companyTypeId = companyTypeId;
        this.web = web;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.maticniBroj = maticniBroj;
        this.pib = pib;
        this.password = password;
        this.about = about;
        this.logo = logo;
        this.lastUpdate = lastUpdate;
        this.createDate = createDate;
        this.status = status;
    }

    public Short getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Short companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public CompanyType getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(CompanyType companyTypeId) {
        this.companyTypeId = companyTypeId;
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
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oglasi.model.Company[ companyId=" + companyId + " ]";
    }

}
