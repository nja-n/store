package com.aeither.store.administration.infrastructure.repository;

import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDomainRepository {
    Optional<User> findByUsername(String username);

    List<User> findByStatusNot(String status);

    Optional<User> findByStoreIdAndStatus(Long storeId, String status);
}
