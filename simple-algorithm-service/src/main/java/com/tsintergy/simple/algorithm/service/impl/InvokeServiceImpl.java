package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.InvokeService;
import com.tsintergy.simple.algorithm.api.ProcessService;
import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import com.tsintergy.simple.algorithm.core.enums.ActionStatusEnum;
import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;
import com.tsintergy.simple.algorithm.core.properties.AlgorithmProperties;
import com.tsintergy.simple.algorithm.core.properties.StoreProperties;
import com.tsintergy.simple.algorithm.core.store.StoreManagerFactory;
import com.tsintergy.simple.algorithm.service.dao.SolverDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Optional;

/**
 * @author angel
 * @description
 * @date 2020/6/8 18:47
 */
@Service("invokeService")
public class InvokeServiceImpl implements InvokeService {

    private static final Logger logger = LoggerFactory.getLogger(InvokeService.class);

    @Resource
    private SolverDAO solverDAO;

    @Autowired
    private ProcessService processService;

    @Autowired
    private AlgorithmProperties algorithmProperties;

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
        File input;
        try {
            input = download(algorithmProperties.getStore(), invokeRequestDTO);
        } catch (StoreException e) {
            logger.error("download Input Fail !", e);
            invokeResultDTO.convertFail(ActionStatusEnum.DOWNLOAD_INPUT_FAIL);
            return invokeResultDTO;
        }
        //3.开始异步执行
        ProcessArgs args = new ProcessArgs(
                solverDO.getSolverDir(),
                solverDO.getSolverName(),
                input.getAbsolutePath(),
                input.getParentFile().getAbsolutePath()+"_OUT",
                invokeRequestDTO.getDebug(),
                invokeRequestDTO.getModel()
        );
        processService.doOpenProcess(args,invokeRequestDTO,solverDO);
        logger.info("doInvokeAlgorithm 结束");
        return invokeResultDTO;
    }

    private File download(StoreProperties storeProperties, InvokeRequestDTO invokeRequestDTO) throws StoreException {
        File download = StoreManagerFactory.createStoreManager(storeProperties.getStoreType())
                .download(
                        StoreManagerFactory.createBaseFileInfo(
                                storeProperties,
                                invokeRequestDTO.getRemoteDir(),
                                invokeRequestDTO.getRemoteFileName()
                        ),
                        algorithmProperties.getWorkspace()+File.separator+invokeRequestDTO.getCaseId()
                );
        return download;
    }

}
