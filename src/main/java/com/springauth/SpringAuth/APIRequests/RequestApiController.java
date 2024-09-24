package com.springauth.SpringAuth.APIRequests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class RequestApiController {

    @Autowired
    private RequestApiRepository requestApiRepository;
    
    @Autowired
    private RequestApiService requestApiService;

    @PostMapping("/requestAPIs")
    public ResponseEntity<String> requestApis(@RequestBody RequestApiDto request) {
        // Convert the list of APIs to a JSON string for storage
        String apisJson = String.join(", ", request.getApikeys()); // Simple example, consider using a JSON library for more complex structures

        // Create a new RequestApi entity
        RequestApi requestApi = new RequestApi();
        requestApi.setProjectId(request.getProjectId());
        requestApi.setApis(apisJson);

        // Save the entity to the database
        requestApiRepository.save(requestApi);

        return new ResponseEntity<>("APIs requested and saved successfully!", HttpStatus.OK);
    }
    
//    @GetMapping("/keys")
//    public ResponseEntity<List<String>> getApiKeys(@RequestParam Integer projectId) {
//        List<String> api = requestAPIsService.getApiKeysByProjectId(projectId);
//        return ResponseEntity.ok(api);
//    }
    
    @GetMapping("/keys")
    public ResponseEntity<String> getApiKeys(@RequestParam Integer projectId) {
        String apiKeys = requestApiService.getApiKeysByProjectId(projectId);
        return ResponseEntity.ok(apiKeys);
    }
    
}
