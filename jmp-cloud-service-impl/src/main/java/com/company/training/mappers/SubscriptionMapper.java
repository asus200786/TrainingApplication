package com.company.training.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.training.Subscription;
import com.company.training.SubscriptionRequestDto;
import com.company.training.SubscriptionResponseDto;
import com.company.training.User;
import com.company.training.repositories.UserRepository;

@Mapper(componentModel = "spring")
public abstract class SubscriptionMapper {
    @Autowired
    private UserRepository userRepository;

    public abstract Subscription subscriptionDtoToSubscription(SubscriptionRequestDto dto);

    public abstract SubscriptionResponseDto subscriptionToSubscriptionDto(Subscription subscription);

    @AfterMapping
    public void updateSubscriptionFromDto(SubscriptionRequestDto dto, Subscription subscription) {
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found for id: " + dto.getUserId()));
            subscription.setUser(user);
        }
    }
}