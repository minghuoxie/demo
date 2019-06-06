package demo.demoservice;

import demo.demodao.DemoUser;

public interface UserService {

    DemoUser findByName(String name);
}
