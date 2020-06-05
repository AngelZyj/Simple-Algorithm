package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.StoreService;

import com.tsintergy.simple.algorithm.api.dto.UploadRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.UploadResultDTO;
import com.tsintergy.simple.algorithm.core.properties.AlgorithmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author angel
 * @description
 * @date 2020/6/5 15:27
 */
public class StoreServiceImpl implements StoreService {

    @Autowired
    private AlgorithmProperties algorithmProperties;

    @Override
    public UploadResultDTO doUploadFile(UploadRequestDTO uploadRequestDTO) {
        validUploadRequestDTO(uploadRequestDTO);
        return null;
    }

    private void validUploadRequestDTO(UploadRequestDTO uploadRequestDTO) {
        Assert.notNull(uploadRequestDTO, "uploadRequestDTO is not allow null !");
        Assert.notNull(uploadRequestDTO.getLocalDir(), "uploadRequestDTO's localDir is not allow null !");
        Assert.notNull(uploadRequestDTO.getCaseId(), "uploadRequestDTO's caseId is not allow null !");
        Assert.notNull(uploadRequestDTO.getAlgorithmType(), "uploadRequestDTO's algorithmType is not allow null !");
        Assert.notNull(uploadRequestDTO.getSolverType(), "uploadRequestDTO's solverType is not allow null !");
        if (StringUtils.isEmpty(uploadRequestDTO.getActionId())) {
            uploadRequestDTO.setActionId(UUID.randomUUID().toString());
        }
    }
}
