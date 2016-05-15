/**
    * ***************************************************
    * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
    *****************************************************
    * 类的详细说明 
    * 
    * @author 东临碣石
    * @Date 2016年4月7日
    * @version 1.00 
*/
package com.uc.system.model;

/**
 * @Description: 政策类别,暂时使用<br>
 *               政策通知<br>
 *               政策文件<br>
 *               政策要闻<br>
 *               政策解读<br>
 * @ClassName: PolicyType
 * @author 落花流水
 * @date 2016年4月7日 下午4:01:47
 */
public class PolicyType
{
    @Override
	public String toString() {
		return "PolicyType [id=" + id + ", name=" + name + ", order=" + order
				+ "]";
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	private String id;
    
    private String name;// 政策类别名称
    
    private int order;//排序字段
    
    /**
     * @Title: PolicyType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param id
     * @param @param name 设定文件
     * @return
     */
    public PolicyType(String id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }
    
    /**
     * @Title: PolicyType
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param 设定文件
     * @return
     */
    public PolicyType()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
