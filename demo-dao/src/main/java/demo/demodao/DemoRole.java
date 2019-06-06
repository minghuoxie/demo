package demo.demodao;

import javax.persistence.*;

@Entity
@Table(name="demo_role")
public class DemoRole {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //自动曾张
    @Column(name="id")
    private Integer id;

    @Column(name="rolename")
    private String rolename;

    @Column(name="uid")
    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
