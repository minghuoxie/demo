package demo.demoservice;

import demo.demodao.DemoJpaBuild;

import java.util.List;

public interface DemoJpaBuildService {
    public List<DemoJpaBuild> insertMany(List<DemoJpaBuild> list);
    public List<DemoJpaBuild> findAll();
    public String findAspect(int index,String arg);

    public void findBySql();
}
