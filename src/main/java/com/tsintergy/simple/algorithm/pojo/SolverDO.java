package com.tsintergy.simple.algorithm.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author angel
 * @description 求解器配置表
 * @date 2020/3/18 16:26
 */
@Data
@Entity
@Table(name = "TSDL_SOLVER",
        uniqueConstraints = {
        @UniqueConstraint(name = "unique_key",columnNames = {"SOLVER_TYPE","ALGORITHM_TYPE","VERSION"})})
public class SolverDO implements Serializable {

    /**
     * 唯一id
     */
    @Id
    @Column(name = "ID", length = 32)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * 求解器类型，如CPLEX,GUROBI
     */
    @Basic
    @Column(name = "SOLVER_TYPE",nullable = false)
    private String solverType;

    /**
     * 求解器对应的算法类型
     */
    @Basic
    @Column(name = "ALGORITHM_TYPE",nullable = false)
    private String algorithmType;

    /**
     * 求解器所在文件夹位置
     */
    @Basic
    @Column(name = "SOLVER_DIR",nullable = false)
    private String solverDir;

    /**
     * 求解器名字，如TopoAnalysis,DASCUC,SecurityCheck
     */
    @Basic
    @Column(name = "SOLVER_NAME", nullable = false)
    private String solverName;

    /**
     * 版本号
     */
    @Basic
    @Column(name = "VERSION",nullable = false)
    private String version;

    /**
     * 是否默认求解器
     */
    @Basic
    @Column(name = "DEFAULT_SOLVER",nullable = false)
    private Boolean defaultSolver;

    /**
     * 从机端口，不知道有什么，南网那版定义的
     */
    @Basic
    @Column(name = "PORT")
    private Integer port;

    /**
     * 创建时间
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

}
