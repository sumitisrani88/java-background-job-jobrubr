package com.gammastack.backgroundjob.repositries;

import com.gammastack.backgroundjob.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findById(int id);
}
