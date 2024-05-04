package com.rajlee.api.teluskoproject.repository;

import com.rajlee.api.teluskoproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
           User getUserByEmail(String email);
}
