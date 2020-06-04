package com.tsintergy.simple.algorithm.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author angel
 * @description
 * @date 2020/3/18 16:28
 */
@Data
@Entity
@Table(name = "TSDL_SOLVER_STEP")
public class SolverStepDO implements Serializable {

    /**
     * 唯一id
     */
    @Id
    @Column(name = "Id", length = 32)
    @GenericGenerator(name = "uuid", strategy = "DEFAULT_STRATEGY")
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * 求解器id
     */
    @Basic
    @Column(name = "SOLVER_ID",nullable = false)
    private String solverId;

    /**
     * 执行步骤
     */
    @Basic
    @Column(name = "STEP_ORDER",nullable = false)
    private Integer stepOrder;

    /**
     * 步骤名称
     */
    @Basic
    @Column(name = "STEP_NAME",nullable = false)
    private String stepName;
}
