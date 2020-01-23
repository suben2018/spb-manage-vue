package com.itsu.spbmanagevue.response;

import com.itsu.spbmanagevue.components.constant.ProjectConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author suben
 * @create time 2020/1/23 15:30
 */
@Getter
@Setter
public class ResonseObj<T> {

    private Integer code;
    private String msg;
    private T data;

    private ResonseObj(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private ResonseObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResonseObj(Integer code) {
        this.code = code;
    }

    public ResonseObj(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static ResonseObj createSuccess(){
        return new ResonseObj(ProjectConstant.SUCCESS_RETURN_CODE);
    }

    public static <T> ResonseObj createSuccess(T data) {
        return new ResonseObj(ProjectConstant.SUCCESS_RETURN_CODE,data);
    }

    public static ResonseObj createError() {
        return new ResonseObj(ProjectConstant.ERROR_RETURN_CODE, ProjectConstant.ERROR_RETURN_MSG);
    }
    public static ResonseObj createError(Integer code) {
        return new ResonseObj(code);
    }

    public static ResonseObj createError(Integer code, String msg) {
        return new ResonseObj(code, msg);
    }

    public static ResonseObj createError(String msg) {
        return new ResonseObj(ProjectConstant.ERROR_RETURN_CODE, msg);
    }

}
