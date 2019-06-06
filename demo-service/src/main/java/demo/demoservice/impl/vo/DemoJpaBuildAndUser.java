package demo.demoservice.impl.vo;

import javax.persistence.Entity;

public class DemoJpaBuildAndUser {
    private Integer id;
    private  String name;
    private  String you;
    private  String qq;
    private  String age;
    private  String addr;
    private  String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DemoJpaBuildAndUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", you='" + you + '\'' +
                ", qq='" + qq + '\'' +
                ", age='" + age + '\'' +
                ", addr='" + addr + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
