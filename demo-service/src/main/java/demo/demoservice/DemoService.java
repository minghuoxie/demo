package demo.demoservice;

import demo.demodao.Demo;

import java.util.List;

public interface DemoService {
    Demo addOne(Demo demo);

    Demo findAll();

    List<Demo> addList(List<Demo> list);

    List<Demo> findList();

    List<Demo> findBetween(Integer one,Integer two);

    Demo findByName(String name);

    Demo findByIdAndName(Integer id,String name);

    List<Demo> findListDemoByParam(String name);

    List<Demo> findAllNoParam();
}
