package com.company.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.training.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}