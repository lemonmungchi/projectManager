package com.example.githubservice.service;

import com.example.githubservice.model.GitHubRepositoryEntity;
import com.example.githubservice.repository.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    @Autowired
    private GitHubRepository gitHubRepository;

    public String getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Save to database
        GitHubRepositoryEntity entity = new GitHubRepositoryEntity();
        entity.setUsername(username);
        entity.setRepositoryName(response); // (Parse and save repository details instead)
        gitHubRepository.save(entity);

        return response;
    }
}
