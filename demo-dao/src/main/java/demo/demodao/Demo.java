package demo.demodao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="demo")
public class Demo implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //自动曾张
    @Column(name="did")
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="sname")
    private  String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Demo(){}
    public Demo(Integer id,String name){
        this.id=id;
        this.name=name;
    }
}
