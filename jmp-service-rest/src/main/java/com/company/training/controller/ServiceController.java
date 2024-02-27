package com.company.training.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.training.SubscriptionRequestDto;
import com.company.training.SubscriptionResponseDto;
import com.company.training.SubscriptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Subscription Management")
@RestController
@RequestMapping("/subscriptions")
public class ServiceController {
    private final SubscriptionService subscriptionService;

    public ServiceController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @ApiOperation(value = "Create a new subscription")
    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> createSubscription(
            @ApiParam(value = "Subscription object to create.", required = true) @RequestBody SubscriptionRequestDto subscriptionRequestDto){

        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.createSubscription(subscriptionRequestDto);
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @ApiOperation(value = "Update an existing subscription")
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(
            @ApiParam(value = "Id of the subscription object to be updated.", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated subscription object.", required = true) @RequestBody SubscriptionRequestDto subscriptionRequestDto){

        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.updateSubscription(subscriptionRequestDto);
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @ApiOperation(value = "Delete a subscription")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(
            @ApiParam(value = "Id of the subscription object to be deleted.", required = true) @PathVariable Long id){

        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Retrieve a subscription with an id")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(
            @ApiParam(value = "Id of the subscription object to be retrieved.", required = true) @PathVariable Long id){

        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.getSubscription(id);
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @ApiOperation(value = "Retrieve all subscriptions")
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscriptions(){
        List<SubscriptionResponseDto> allSubscriptionsResponseDto = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(allSubscriptionsResponseDto);
    }
}