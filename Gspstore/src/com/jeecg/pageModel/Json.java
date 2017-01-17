package com.jeecg.pageModel;

/**
 * JSON模型
 * 
 * @author zhangdaihao
 * 
 */
public class Json implements java.io.Serializable {

	private boolean success = false;// 是否成功
	private String msg = "";// 提示信息
	private Object obj = null;// 其他信息

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	//update-begin--Author:xiewenyu  Date:20130312 for:异常处理
	@Override
	public String toString() {
		return "{success:"+success+",msg:\""+msg+"\"}";
	}
	//update-end--Author:xiewenyu  Date:20130312 for:异常处理
	


}
