package model;
// Generated 09.Nis.2017 18:52:07 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name="users"
    ,catalog="dp"
    , uniqueConstraints = @UniqueConstraint(columnNames="id") 
)
public class Users  implements java.io.Serializable {


     private String username;
     private int id;
     private String password;
     private String recordnum;
     private String email;
     private String phone;
     private String department;
     private String departmanager;
     private Set<Travel> travels = new HashSet<Travel>(0);
     private Set<Userroles> userroleses = new HashSet<Userroles>(0);

    public Users() {
    }
    
    

	
    public Users(int id, String password, String recordnum, String email, String phone, String department, String departmanager) {
        this.id = id;
        this.password = password;
        this.recordnum = recordnum;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.departmanager = departmanager;
    }
    public Users(int id, String password, String recordnum, String email, String phone, String department, String departmanager, Set<Travel> travels, Set<Userroles> userroleses) {
       this.id = id;
       this.password = password;
       this.recordnum = recordnum;
       this.email = email;
       this.phone = phone;
       this.department = department;
       this.departmanager = departmanager;
       this.travels = travels;
       this.userroleses = userroleses;
    }
   
     @Id 

    
    @Column(name="username", unique=true, nullable=false, length=45)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="password", nullable=false, length=45)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="recordnum", nullable=false, length=45)
    public String getRecordnum() {
        return this.recordnum;
    }
    
    public void setRecordnum(String recordnum) {
        this.recordnum = recordnum;
    }

    
    @Column(name="email", nullable=false, length=60)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="phone", nullable=false, length=20)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="department", nullable=false, length=20)
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }

    
    @Column(name="departmanager", nullable=false, length=60)
    public String getDepartmanager() {
        return this.departmanager;
    }
    
    public void setDepartmanager(String departmanager) {
        this.departmanager = departmanager;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<Travel> getTravels() {
        return this.travels;
    }
    
    public void setTravels(Set<Travel> travels) {
        this.travels = travels;
        
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<Userroles> getUserroleses() {
        return this.userroleses;
    }
    
    public void setUserroleses(Set<Userroles> userroleses) {
        this.userroleses = userroleses;
    }




}


