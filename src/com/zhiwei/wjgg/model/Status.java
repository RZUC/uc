package com.zhiwei.wjgg.model;

import java.util.Date;
import java.util.List;

public class Status {


	private User user = null; // 作者信息
	private Date createdAt; // status创建时间
	private String id; // status id
	private String mid; // 微博MID
	private long idstr; // 保留字段，请勿使用
	private String text; // 微博内容
	private String source; // 微博来源
	private boolean favorited; // 是否已收藏
	private boolean truncated;
	private long inReplyToStatusId; // 回复ID
	private long inReplyToUserId; // 回复人ID
	private String inReplyToScreenName; // 回复人昵称
	private String thumbnailPic; // 微博内容中的图片的缩略地址
	private String bmiddlePic; // 中型图片
	private String originalPic; // 原始图片
	private Status retweetedStatus = null; // 转发的博文，内容为status，如果不是转发，则没有此字段
	private String geo; // 地理信息，保存经纬度，没有时不返回此字段
	private double latitude = -1; // 纬度
	private double longitude = -1; // 经度
	private int repostsCount; // 转发数
	private int commentsCount; // 评论数
	private String annotations; // 元数据，没有时不返回此字段
	private int mlevel;
	private List<String> pic_urls;
	
	public Status() {

	}
 

	private void getGeoInfo(String geo) {
		StringBuffer value = new StringBuffer();
		for (char c : geo.toCharArray()) {
			if (c > 45 && c < 58) {
				value.append(c);
			}
			if (c == 44) {
				if (value.length() > 0) {
					latitude = Double.parseDouble(value.toString());
					value.delete(0, value.length());
				}
			}
		}
		longitude = Double.parseDouble(value.toString());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }


    /**
     * @return the createdAt
     */
    public Date getCreatedAt()
    {
        return createdAt;
    }


    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }


    /**
     * @return the mid
     */
    public String getMid()
    {
        return mid;
    }


    /**
     * @return the idstr
     */
    public long getIdstr()
    {
        return idstr;
    }


    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }


    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }


    /**
     * @return the favorited
     */
    public boolean isFavorited()
    {
        return favorited;
    }


    /**
     * @return the truncated
     */
    public boolean isTruncated()
    {
        return truncated;
    }


    /**
     * @return the inReplyToStatusId
     */
    public long getInReplyToStatusId()
    {
        return inReplyToStatusId;
    }


    /**
     * @return the inReplyToUserId
     */
    public long getInReplyToUserId()
    {
        return inReplyToUserId;
    }


    /**
     * @return the inReplyToScreenName
     */
    public String getInReplyToScreenName()
    {
        return inReplyToScreenName;
    }


    /**
     * @return the thumbnailPic
     */
    public String getThumbnailPic()
    {
        return thumbnailPic;
    }


    /**
     * @return the bmiddlePic
     */
    public String getBmiddlePic()
    {
        return bmiddlePic;
    }


    /**
     * @return the originalPic
     */
    public String getOriginalPic()
    {
        return originalPic;
    }


    /**
     * @return the retweetedStatus
     */
    public Status getRetweetedStatus()
    {
        return retweetedStatus;
    }


    /**
     * @return the geo
     */
    public String getGeo()
    {
        return geo;
    }


    /**
     * @return the latitude
     */
    public double getLatitude()
    {
        return latitude;
    }


    /**
     * @return the longitude
     */
    public double getLongitude()
    {
        return longitude;
    }


    /**
     * @return the repostsCount
     */
    public int getRepostsCount()
    {
        return repostsCount;
    }


    /**
     * @return the commentsCount
     */
    public int getCommentsCount()
    {
        return commentsCount;
    }


    /**
     * @return the annotations
     */
    public String getAnnotations()
    {
        return annotations;
    }


    /**
     * @return the mlevel
     */
    public int getMlevel()
    {
        return mlevel;
    }


    /**
     * @return the pic_urls
     */
    public List<String> getPic_urls()
    {
        return pic_urls;
    }


    /**
     * @param user the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }


    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }


    /**
     * @param mid the mid to set
     */
    public void setMid(String mid)
    {
        this.mid = mid;
    }


    /**
     * @param idstr the idstr to set
     */
    public void setIdstr(long idstr)
    {
        this.idstr = idstr;
    }


    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }


    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }


    /**
     * @param favorited the favorited to set
     */
    public void setFavorited(boolean favorited)
    {
        this.favorited = favorited;
    }


    /**
     * @param truncated the truncated to set
     */
    public void setTruncated(boolean truncated)
    {
        this.truncated = truncated;
    }


    /**
     * @param inReplyToStatusId the inReplyToStatusId to set
     */
    public void setInReplyToStatusId(long inReplyToStatusId)
    {
        this.inReplyToStatusId = inReplyToStatusId;
    }


    /**
     * @param inReplyToUserId the inReplyToUserId to set
     */
    public void setInReplyToUserId(long inReplyToUserId)
    {
        this.inReplyToUserId = inReplyToUserId;
    }


    /**
     * @param inReplyToScreenName the inReplyToScreenName to set
     */
    public void setInReplyToScreenName(String inReplyToScreenName)
    {
        this.inReplyToScreenName = inReplyToScreenName;
    }


    /**
     * @param thumbnailPic the thumbnailPic to set
     */
    public void setThumbnailPic(String thumbnailPic)
    {
        this.thumbnailPic = thumbnailPic;
    }


    /**
     * @param bmiddlePic the bmiddlePic to set
     */
    public void setBmiddlePic(String bmiddlePic)
    {
        this.bmiddlePic = bmiddlePic;
    }


    /**
     * @param originalPic the originalPic to set
     */
    public void setOriginalPic(String originalPic)
    {
        this.originalPic = originalPic;
    }


    /**
     * @param retweetedStatus the retweetedStatus to set
     */
    public void setRetweetedStatus(Status retweetedStatus)
    {
        this.retweetedStatus = retweetedStatus;
    }


    /**
     * @param geo the geo to set
     */
    public void setGeo(String geo)
    {
        this.geo = geo;
    }


    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }


    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }


    /**
     * @param repostsCount the repostsCount to set
     */
    public void setRepostsCount(int repostsCount)
    {
        this.repostsCount = repostsCount;
    }


    /**
     * @param commentsCount the commentsCount to set
     */
    public void setCommentsCount(int commentsCount)
    {
        this.commentsCount = commentsCount;
    }


    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(String annotations)
    {
        this.annotations = annotations;
    }


    /**
     * @param mlevel the mlevel to set
     */
    public void setMlevel(int mlevel)
    {
        this.mlevel = mlevel;
    }


    /**
     * @param pic_urls the pic_urls to set
     */
    public void setPic_urls(List<String> pic_urls)
    {
        this.pic_urls = pic_urls;
    }

}
