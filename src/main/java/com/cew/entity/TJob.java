package com.cew.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenchaofei on 2017/3/12.
 */
@Entity
@Table(name = "t_job")
public class TJob implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static byte STATUS_ONLINE = 0;
    public final static byte STATUS_OFFLINE = 1;

    public TJob() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 职位
     */
    @Column(name = "title")
    private String title;

    /**
     * 月薪
     */
    @Column(name = "salary")
    private String salary;

    /**
     * 地点
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 经验
     */
    @Column(name = "experience")
    private String experience;

    /**
     * 福利
     */
    @Column(name = "welfare")
    private String welfare;

    /**
     * 岗位职责
     */
    @Column(name = "description", columnDefinition="text")
    private String description;

    /**
     * 任职要求
     */
    @Column(name = "requirements", columnDefinition="text")
    private String requirements;

    /**
     * 在线和下线状态
     */
    @Column(name = "status")
    private byte status;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "create_at")
    private Date createAt;

    @PrePersist
    public void prePersist(){
        this.updateAt = new Date();
        this.createAt = this.updateAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
