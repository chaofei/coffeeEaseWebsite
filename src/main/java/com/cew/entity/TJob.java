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

    public TJob(Long id, String title) {
        this.setId(id);
        this.title = title;
    }


    public TJob() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

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

}
