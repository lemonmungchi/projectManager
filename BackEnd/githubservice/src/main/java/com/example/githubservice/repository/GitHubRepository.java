package com.example.githubservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.githubservice.model.GitHubRepositoryEntity;

public interface GitHubRepository extends JpaRepository<GitHubRepositoryEntity, Long> {
}

