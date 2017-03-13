package com.cew.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chenchaofei on 2017/3/13.
 */
@Entity
@Table(name = "t_resume")
public class TResume implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "attachment")
    private String attachment;
}
