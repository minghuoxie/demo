package demo.config.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Service
public class WebSocketUserServiceImpl implements WebSocketUserService {

    private static CopyOnWriteArraySet<String> ONLINE_USER_LIST = new CopyOnWriteArraySet<>();
    static {
        ONLINE_USER_LIST.add("one");
        ONLINE_USER_LIST.add("two");
    }
    @Override
    public List<String> getOnlineUsers() {
        return ONLINE_USER_LIST.stream().collect(Collectors.toList());
    }

    @Override
    public String getOnlineUser(int userId) {
        if(userId>=0&&userId<ONLINE_USER_LIST.size()){
            return ONLINE_USER_LIST.stream().collect(Collectors.toList()).get(userId);
        }
        return ONLINE_USER_LIST.stream().collect(Collectors.toList()).get(0);
    }

    @Override
    public void offlineUser(String user) {
        ONLINE_USER_LIST.remove(user);
    }

    @Override
    public void onlineUser(String user) {
        ONLINE_USER_LIST.add(user);
    }

    @Override
    public int getOnlineUserCount() {
        return ONLINE_USER_LIST.size();
    }
}
