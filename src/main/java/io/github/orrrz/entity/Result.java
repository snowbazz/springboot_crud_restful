package io.github.orrrz.entity;

/**
 * Created by icejam.
 * 控制器返回结果类
 */
public class Result {
    private Boolean flag;// 返回结构状态
    private Integer code;// 返回状态码
    private String message;//返回消息
    private Object data; // 返回数据内容

    public Result() {
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
