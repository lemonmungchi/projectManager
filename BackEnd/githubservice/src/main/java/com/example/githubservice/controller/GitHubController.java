package com.example.githubservice.controller;

import com.example.githubservice.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/repos/{username}")
    public String getRepositories(@PathVariable String username) {
        return gitHubService.getRepositories(username);
    }
}
