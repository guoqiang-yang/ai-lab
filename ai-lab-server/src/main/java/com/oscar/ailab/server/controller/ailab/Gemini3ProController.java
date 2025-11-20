package com.oscar.ailab.server.controller.ailab;

import com.oscar.ailab.client.dto.ailab.ConfigResponseDTO;
import com.oscar.ailab.server.domain.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/gemini3/pro")
public class Gemini3ProController {
    
    public ResultWrapper<ConfigResponseDTO> getConfig() {
        return ResultWrapper.success(ConfigResponseDTO.builder()
                .llmName("Gemini3.5 Pro")
                .lleVersion("1.0.0")
                .llmDesc("Gemini3.5 Pro is a state-of-the-art language model developed by Google DeepMind, designed to deliver advanced natural language understanding and generation capabilities for a wide range of applications.")
                .build()
        );
    }


    @GetMapping("/model")
    public ResultWrapper<String> getModel(String model) {
        model = StringUtils.isEmpty( model ) ? "Gemini3.5 Pro" : model;
        return ResultWrapper.success(model);
    }
}
