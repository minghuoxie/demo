package demo.demoservice.impl;

import demo.demodao.DemoUser;
import demo.demodao.DemoUserRepository;
import demo.demoservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImple implements UserService {

    @Override
    public DemoUser findByName(String name) {
        return null;
    }
}
