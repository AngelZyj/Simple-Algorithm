package com.tsintergy.simple.algorithm.service.dao;


import com.tsintergy.simple.algorithm.api.pojo.SolverLogDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author angel
 * @date 2020/3/19 9:48
 */
public interface SolverLogDAO extends JpaRepository<SolverLogDO,String> {
}
