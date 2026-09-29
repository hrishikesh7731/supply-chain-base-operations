package com.supply_chain_easy.supply_chain_base_operations.models;

import com.supply_chain_easy.supply_chain_base_operations.enums.AuthenticationProvider;
import com.supply_chain_easy.supply_chain_base_operations.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User extends GlobalRecord {
    // =========================
    // Identity Information
    // =========================
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String profileImageUrl;
    private UserStatus status;
    private String currency;

    // =========================
    // Location Information
    // =========================
    private String address;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String locationId;
    private String warehouseId;
    private AuthenticationProvider authenticationProvider;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private Boolean twoFactorEnabled;
    private Instant lastLoginAt;
    private Instant passwordChangedAt;
    private Integer failedLoginAttempts;
    private Boolean accountLocked;

    // =========================
    // Preferences
    // =========================
    private String preferredLanguage;
    private String preferredCurrency;
    private String timeZone;

    // =========================
    // Audit Information
    // =========================
    private Boolean active;
    private Instant lastActiveAt;

    @ManyToMany
    private List<Role> roles;
}