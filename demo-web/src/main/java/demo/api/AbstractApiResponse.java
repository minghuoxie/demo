package demo.api;

import java.io.Serializable;

public abstract class AbstractApiResponse implements Serializable {
    private static final long serialVersionUID = 7080173158276771154L;

    private boolean success;

    /**
     * 获取是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     *
     * @param success
     *            成功标识
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
