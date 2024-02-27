package com.company.training;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscriptions();
}