package demo.demodao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="demo_permission")
public class DemoPermission implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //自动曾张
    @Column(name="pid")
    private Integer pid;

    @Column(name="pmethod")
    private String pmethod;

    @Column(name="purl")
    private String purl;

    @Column(name="uid")
    private Integer uid;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPmethod() {
        return pmethod;
    }

    public void setPmethod(String pmethod) {
        this.pmethod = pmethod;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
