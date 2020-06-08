package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.InvokeService;
import com.tsintergy.simple.algorithm.api.ProcessService;
import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import com.tsintergy.simple.algorithm.core.enums.ActionStatusEnum;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;
import com.tsintergy.simple.algorithm.service.dao.SolverDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author angel
 * @description
 * @date 2020/6/8 18:47
 */
@Service("invokeService")
public class InvokeServiceImpl implements InvokeService {

    @Resource
    private SolverDAO solverDAO;

    @Autowired
    private ProcessService processService;


    @Override
    public InvokeResultDTO doInvokeAlgorithm(InvokeRequestDTO invokeRequestDTO) {
        InvokeResultDTO invokeResultDTO = new InvokeResultDTO(invokeRequestDTO.getCaseId(), invokeRequestDTO.getActionId(), ActionStatusEnum.SUCCESS);
        //1.查找对应的solverDO
        SolverDO solverCondition = new SolverDO();
        solverCondition.setAlgorithmType(invokeRequestDTO.getAlgorithmType());
        solverCondition.setSolverType(invokeRequestDTO.getSolverType());
        solverCondition.setVersion(invokeRequestDTO.getVersion());
        Optional<SolverDO> solverOptional = solverDAO.findOne(Example.of(solverCondition));
        if (!solverOptional.isPresent()) {
            invokeResultDTO.convertFail(ActionStatusEnum.NO_SOLVER);
            return invokeResultDTO;
        }
        SolverDO solverDO = solverOptional.get();
        //2.下载输入

        //3.开始异步执行
        ProcessArgs args = new ProcessArgs(
                solverDO.getSolverDir(),
                solverDO.getSolverName(),
                "IN",
                "OUT",
                invokeRequestDTO.getDebug(),
                invokeRequestDTO.getModel()
        );

        processService.doOpenProcess(args);
        return invokeResultDTO;
    }

}
