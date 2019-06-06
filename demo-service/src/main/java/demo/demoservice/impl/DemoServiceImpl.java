package demo.demoservice.impl;

import demo.demodao.Demo;
import demo.demodao.DemoRepository;
import demo.demoservice.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;
    @Override
    public Demo addOne(Demo demo) {
        return this.demoRepository.save(demo);
    }

    @Override
    public Demo findAll() {
        Demo demo=demoRepository.findById(new Integer(1));
        //System.out.println("service---"+demo.getName());
        return demo;
    }

    @Override
    public List<Demo> addList(List<Demo> list) {
       return demoRepository.save(list);
    }

    @Override
    public List<Demo> findList() {
        System.out.println("---------------");
        return debugFindList();
    }

    private List<Demo> debugFindList(){
        return demoRepository.findAll();
    }

    @Override
    public List<Demo> findBetween(Integer one, Integer two) {
        return demoRepository.findAllByIdBetween(one,two);
    }

    @Override
    public Demo findByName(String name) {
        return demoRepository.findByName(name);
    }

    @Override
    public Demo findByIdAndName(Integer id, String name) {
        return demoRepository.findByIdAndName(id,name);
    }

    @Override
    public List<Demo> findListDemoByParam(String name) {
        return demoRepository.findByParam(name);
    }

    @Override
    public List<Demo> findAllNoParam() {
        return demoRepository.findAllNoParam();
    }

}
