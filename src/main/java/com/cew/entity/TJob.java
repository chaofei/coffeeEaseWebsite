package com.cew.entity;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenchaofei on 2017/3/12.
 */
@Entity
@Table(name = "t_job")
public class TJob implements Serializable {
    private static final long serialVersionUID = 1L;

    public final static byte STATUS_ONLINE = 0;
    public final static byte STATUS_OFFLINE = 1;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private byte status;

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
