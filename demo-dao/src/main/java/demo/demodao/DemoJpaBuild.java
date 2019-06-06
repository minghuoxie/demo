package demo.demodao;

import javax.persistence.*;

@Entity
@Table(name="demo_jpa_build")
public class DemoJpaBuild {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //自动曾张
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private  String name;

    @Column(name="you")
    private  String you;

    @Column(name="qq")
    private  String qq;

    @Column(name="age")
    private  String age;

    @Column(name="addr")
    private  String addr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYou() {
        return you;
    }

    public void setYou(String you) {
        this.you = you;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
