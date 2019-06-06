package demo.demodao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="demo_user")
public class DemoUser implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //自动曾张
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private  String username;

    @Column(name="password")
    private  String password;

    @OneToMany
    @JoinColumn(name="uid")
    private List<DemoRole> roles;

    public DemoUser(){}

    public List<DemoRole> getRoles() {
        return roles;
    }

    public void setRoles(List<DemoRole> roles) {
        this.roles = roles;
    }

    public DemoUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getSid() {
        return id;
    }

    public void setSid(Integer id) {
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
