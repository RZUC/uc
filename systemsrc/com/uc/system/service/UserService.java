/** * *************************************************** * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. * ***************************************************** *  * */package com.uc.system.service;import java.util.List;import com.uc.system.model.Message;import com.uc.system.model.Page;import com.uc.system.model.Query;import com.uc.system.model.User;/** * @author * */public interface UserService {	/**	 * @Title: findAllUsers	 * @Description: 查询所有用户	 * @param @return 设定文件	 * @return List<User> 返回类型 返回用户列表	 */	public List<User> findAllUsers(Query query, Page page);		/**	 * @Title: findAllUsers	 * @Description: 查询所有用户	 * @param @return 设定文件	 * @return List<User> 返回类型 返回用户列表	 */	public int totalCount();		public User add(User user);	public Message del(String id);	public User modify(User user);	public User findByUid(String uid);	public User findByusernameAndPassword(String username, String password);	public User register(User user);}