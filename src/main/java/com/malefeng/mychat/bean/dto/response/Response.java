package com.malefeng.mychat.bean.dto.response;

import com.malefeng.mychat.bean.enums.RuntimeErrorEnum;
import com.malefeng.mychat.bean.exception.MyException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**  
* @ClassName: Response.java  
* @Description: 返回封装类
* @author paradise  
* @date 2019年8月30日    
*/
@Data
public class Response<T> implements Serializable{

	private static final long serialVersionUID = 7016581791275696059L;
	
	@ApiModelProperty(value = "返回包代码，业务执行正常默认返回都是200；如果业务出错，则返回具体的错误代码")
	private int code;

	@ApiModelProperty(value = "业务异常说明")
	private String msg;
	
	@ApiModelProperty(value = "返回对象") 
	private T data;
	
	@ApiModelProperty(value = "扩展参数(用于返回列表总条数，方便前端分页)") 
	private Long extCount;
	
	public Response() {}
	
	public Response(T data){
		this.setCode(RuntimeErrorEnum.SUCCESS.getCode());
		this.msg = RuntimeErrorEnum.SUCCESS.getMsg();
		this.data = data;
	}

	public Response(RuntimeErrorEnum error){
		this.setCode(error.getCode());
		this.setMsg(error.getMsg());
	}
	
	public Response(T data, Long count){
		this.setCode(RuntimeErrorEnum.SUCCESS.getCode());
		this.msg = RuntimeErrorEnum.SUCCESS.getMsg();
		this.data = data;
		this.extCount = count;
	}

	public Response success(int code, String msg) {
			this.setCode(code);
			this.setMsg(msg);
			return this;
	}
	
	public Response success(int code, String msg,T data) {
		this.setCode(code);
		this.setMsg(msg);
		this.data = data;
		return this;
	}
	
	public Response error(int code, String msg) {
		this.setCode(code);
		this.setMsg(msg);
		return this;
	}

	public Response error(RuntimeErrorEnum error){
		this.setCode(error.getCode());
		this.setMsg(error.getMsg());
		return this;
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

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	public Long getExtCount() {
		return extCount;
	}

	public void setExtCount(Long extCount) {
		this.extCount = extCount;
	}

	@SuppressWarnings("rawtypes")
	public static Response<?> getGlobalBaseBean(Exception e){
		Response<?> resp = new Response();
		resp.setCode(Integer.valueOf(RuntimeErrorEnum.ERROR.getCode()));
		resp.setMsg(RuntimeErrorEnum.ERROR.getMsg());
		return resp;
	}

	@SuppressWarnings("rawtypes")
	public static Response<?> getCustomBaseBean(MyException e){
		Response<?> resp = new Response();
		resp.setCode(Integer.valueOf(e.getCode()));
		resp.setMsg(e.getErrorMsg());
		return resp;
	}

}
