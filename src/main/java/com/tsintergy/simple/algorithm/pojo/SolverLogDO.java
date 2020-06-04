package com.tsintergy.simple.algorithm.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author angel
 * @description
 * @date 2020/3/18 16:28
 */
@Data
@Entity
@Table(name = "TSDL_SOLVER_LOG")
public class SolverLogDO implements Serializable {

    @Id
    @Column(name = "ID", length = 32)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Basic
    @Column(name = "ACTION_ID", nullable = false)
    private String actionId;

    @Basic
    @Column(name = "CASE_ID")
    private String caseId;

    @Basic
    @Column(name = "SOLVER_ID")
    private String solverId;

    @Basic
    @Column(name = "STEP_ORDER")
    private Integer stepOrder;

    @Basic
    @Column(name = "STEP_NAME")
    private String stepName;

    @Basic
    @Column(name = "LEVEL")
    private Integer level;

    @Basic
    @Column(name = "MESSAGE")
    private String message;

    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;
}
