package com.aeither.store.administration.domain.repository;

import com.aeither.store.administration.domain.model.User;
import java.util.Optional;
import java.util.List;

public interface UserDomainRepository {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findByStatusNot(String status);
}
