package com.springauth.SpringAuth.APIRequests;

import java.util.List;

import lombok.Data;

@Data
public class RequestApiDto {
    private Integer projectId;
    private List<String> apikeys;
}
