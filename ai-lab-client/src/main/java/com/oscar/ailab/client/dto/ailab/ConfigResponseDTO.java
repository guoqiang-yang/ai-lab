package com.oscar.ailab.client.dto.ailab;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigResponseDTO {

    private String llmName;
    private String lleVersion;
    private String llmDesc;
}
