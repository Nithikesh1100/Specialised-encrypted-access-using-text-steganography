package com.springauth.SpringAuth.APIRequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RequestApiService {

    @Autowired
    private RequestApiRepository requestApiRepository;

//    public List<String> getApiKeysByProjectId(Integer projectId) {
//        // Assuming you have a method to fetch data from the database
//        String apiKeysCsv = requestAPIsRepository.findApiKeysByProjectId(projectId);
//        // Split CSV string into a list of API keys
//        return List.of(apiKeysCsv.split(","));
//    }
    
//    public List<String> getApiKeysByProjectId(Integer projectId) {
//        String apiKeysCsv = requestApiRepository.findApiKeysByProjectId(projectId);
//        return apiKeysCsv != null ? Arrays.asList(apiKeysCsv.split(",")) : Collections.emptyList();
//    }
    
    public String getApiKeysByProjectId(Integer projectId) {
        return requestApiRepository.findApiKeysByProjectId(projectId);
    }
}
