package com.spider.web.entity ;


import javax.persistence.*;

@Entity
@Table(name = "house_info")
public class HouseInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String url;
  private String title;
  private java.sql.Timestamp publishTime;
  private String content;
  private java.sql.Timestamp createTime;
  private long hits;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public java.sql.Timestamp getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(java.sql.Timestamp publishTime) {
    this.publishTime = publishTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getHits() {
    return hits;
  }

  public void setHits(long hits) {
    this.hits = hits;
  }

}
