package demo.demoservice.impl;

import demo.demodao.DemoPermission;
import demo.demodao.DemoPermissionRepository;
import demo.demoservice.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private DemoPermissionRepository permissionRepository;
    @Override
    public List<DemoPermission> findByUid(Integer uid) {
        return permissionRepository.findByUid(uid);
    }
}
