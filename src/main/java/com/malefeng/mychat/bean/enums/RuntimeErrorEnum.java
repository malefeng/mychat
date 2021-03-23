package com.malefeng.mychat.bean.enums;

/**
* @Description: 运行异常enum
* @author mlf
*/
public enum RuntimeErrorEnum {

	SUCCESS(200, "OK"),
	FAILED(-100, "请求失败"),
	ERROR(500,"Internal Server Error"),

	BAD_REQUEST(400,"Bad Request"),
	UN_AUTH(401,"Unauthorized");

	private int code;

	private String msg;

	RuntimeErrorEnum(final int code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static RuntimeErrorEnum fromValue(String value) {
		for (RuntimeErrorEnum at : RuntimeErrorEnum.values()) {
			if (at.getMsg().equals(value)) {
				return at;
			}
		}
		return null;
	}

	public boolean equals(RuntimeErrorEnum runtimeErrorEnum){
		if(null == runtimeErrorEnum){
			return false;
		}
		return runtimeErrorEnum.getCode() == this.getCode();
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
