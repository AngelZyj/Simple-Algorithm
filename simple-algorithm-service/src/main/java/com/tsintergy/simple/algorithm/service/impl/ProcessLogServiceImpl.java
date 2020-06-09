package com.tsintergy.simple.algorithm.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsintergy.simple.algorithm.api.ProcessLogService;
import com.tsintergy.simple.algorithm.api.pojo.SolverLogDO;
import com.tsintergy.simple.algorithm.api.pojo.SolverStepDO;
import com.tsintergy.simple.algorithm.core.process.SolverInteractionMessage;
import com.tsintergy.simple.algorithm.service.dao.SolverLogDAO;
import com.tsintergy.simple.algorithm.service.dao.SolverStepDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author angel
 * @description
 * @date 2020/6/9 14:39
 */
public class ProcessLogServiceImpl implements ProcessLogService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    @Resource
    private SolverStepDAO solverStepDAO;

    @Resource
    private SolverLogDAO solverLogDAO;

    /**
     * key=solverId+stepOrder
     */
    private Map<String, SolverStepDO> solverStepDOMap = new HashMap<>();

    @Override
    public void doSaveSolverLog(String actionId, String caseId, String solverId, String line) {
        if (line.startsWith("#{") && line.endsWith("}")) {
            String json = line.substring(1);
            try {
                SolverInteractionMessage solverInteractionMessage = new ObjectMapper().readValue(json, SolverInteractionMessage.class);
                SolverLogDO solverLogDO = new SolverLogDO();
                solverLogDO.setActionId(actionId);
                solverLogDO.setCaseId(caseId);
                solverLogDO.setSolverId(solverId);
                solverLogDO.setStepOrder(solverInteractionMessage.getStep());
                solverLogDO.setLevel(solverInteractionMessage.getLevel());
                solverLogDO.setMessage(solverInteractionMessage.getMessage());
                solverLogDO.setCreateTime(new Date());
                solverLogDO.setStepName(getSolverStepDO(solverLogDO.getSolverId(), solverLogDO.getStepOrder()).map(SolverStepDO::getStepName).orElse(null));
                solverLogDAO.save(solverLogDO);
            } catch (JsonProcessingException e) {
                logger.error("{} parse Fail!",line,e);
            }
        }
    }

    public Optional<SolverStepDO> getSolverStepDO(String solverId, Integer stepOrder) {
        //如果solverStepDOMap不存在此算法的步骤，则查询数据库确认一次
        if (Objects.isNull(solverStepDOMap.get(solverId + stepOrder))) {
            SolverStepDO solverStepCondition = new SolverStepDO();
            solverStepCondition.setSolverId(solverId);
            List<SolverStepDO> stepDOS = solverStepDAO.findAll(Example.of(solverStepCondition));
            Map<String, SolverStepDO> stepDOMap = stepDOS.stream().collect(Collectors.toMap(
                    solverStepDO -> solverStepDO.getSolverId() + solverStepDO.getStepOrder()
                    , Function.identity()
            ));
            solverStepDOMap.putAll(stepDOMap);
        }
        return Optional.ofNullable(solverStepDOMap.get(solverId + stepOrder));
    }
}
