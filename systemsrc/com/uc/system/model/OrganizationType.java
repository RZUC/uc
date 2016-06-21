package com.uc.system.model;

/** 
 * @ClassName: ServiceType 
 * @Description: TODO(服务机构实体类) 
 * @author Administrator 
 * @date 2016年6月21日 下午10:08:52  
 */
public class OrganizationType {
	private int id;
	private String type;

	public int getId() {
		return id;
	}

	public OrganizationType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
