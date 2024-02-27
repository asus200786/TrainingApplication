package com.company.training.services;

import java.util.List;
import java.util.stream.Collectors;

import com.company.training.Subscription;
import com.company.training.SubscriptionRequestDto;
import com.company.training.SubscriptionResponseDto;
import com.company.training.SubscriptionService;
import com.company.training.mappers.SubscriptionMapper;
import com.company.training.repositories.SubscriptionRepository;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionMapper.subscriptionDtoToSubscription(subscriptionRequestDto);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.subscriptionToSubscriptionDto(subscription);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionRepository.findById(subscriptionRequestDto.getId()).orElseThrow(() -> new RuntimeException("Subscription not found"));
        subscriptionMapper.updateSubscriptionFromDto(subscriptionRequestDto, subscription);
        subscriptionRepository.save(subscription);
        return subscriptionMapper.subscriptionToSubscriptionDto(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new RuntimeException("Subscription not found"));
        return subscriptionMapper.subscriptionToSubscriptionDto(subscription);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::subscriptionToSubscriptionDto)
                .collect(Collectors.toList());
    }
}