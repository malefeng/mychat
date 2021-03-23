package com.malefeng.mychat.bean.exception;


import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;

/**
 * @Auther: zhangxin
 * @Date: 2019-09-02 19:12
 * @Description: cn.space.common.exception
 * @Version: 1.0
 */

public class MyException extends RuntimeException {
	private static final long serialVersionUID = -6935069554276227666L;
	private int code;
    private String errorMsg;

    public MyException(int errorCode, String errorMsg, String ext) {
    	super(errorMsg);
        this.code = errorCode;
        this.errorMsg = errorMsg + "_" + ext;
    }

    public MyException(int errorCode, String errorMsg) {
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }

    public MyException(RuntimeErrorEnum e){
    	if(e!=null){
			this.code = e.getCode();
			this.errorMsg = e.getMsg();
		}
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
