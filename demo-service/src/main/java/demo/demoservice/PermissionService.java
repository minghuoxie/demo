package demo.demoservice;

import demo.demodao.DemoPermission;

import java.util.List;

public interface PermissionService {
    List<DemoPermission> findByUid(Integer uid);
}
