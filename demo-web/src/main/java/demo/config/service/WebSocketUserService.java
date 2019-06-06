package demo.config.service;

import java.util.List;

public interface WebSocketUserService {
    /**
     * 获取全部的在线用户
     *
     * @return
     */
    List<String> getOnlineUsers();

    /**
     * 获取指定用户id的用户，如果不存在返回null
     *
     * @param userId 用户id
     * @return
     */
    String getOnlineUser(int userId);

    /**
     * 用户下线
     *
     * @param user 要下线的用户
     */
    void offlineUser(String user);

    /**
     * 用户上线
     *
     * @param user 要上线的用户
     */
    void onlineUser(String user);

    /**
     * 获取在线的用户人数
     *
     * @return
     */
    int getOnlineUserCount();
}
