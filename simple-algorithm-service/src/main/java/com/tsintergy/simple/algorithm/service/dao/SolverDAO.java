package com.tsintergy.simple.algorithm.service.dao;


import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author angel
 * @date 2020/3/19 9:47
 */
public interface SolverDAO extends JpaRepository<SolverDO, String> {

}
