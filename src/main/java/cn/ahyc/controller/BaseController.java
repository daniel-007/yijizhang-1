/**
 * Copyright (c) 2015, AnHui Xin Hua She Group. All rights reserved.
 */
package cn.ahyc.controller;

/**
 * BaseController
 * @author sanlai_lee@qq.com
 *
 */
public abstract class BaseController {

	String pathPrefix= "module/";

	/**
	 * 根据路径前缀获取视图路径
	 * @param viewName
	 * @return
	 */
	String view(String viewName){
		return view(this.pathPrefix, viewName);
	}

	/**
	 * 根据路径前缀获取视图路径
	 * @param pathPrefix
	 * @param viewName
	 * @return
	 */
	String view(String pathPrefix, String viewName){
		return pathPrefix+viewName;
	}

}
