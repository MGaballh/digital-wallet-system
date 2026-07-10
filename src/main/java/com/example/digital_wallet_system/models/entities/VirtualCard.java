package com.example.digital_wallet_system.models.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "virtual_cards")
public class VirtualCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(name = "card_number", nullable = false, unique = true, updatable = false)
    private String cardNumber;

    @Column(nullable = false, length = 3)
    private String cvv;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal limitAmount;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Version
    @Builder.Default
    @Column(name = "version", nullable = false)
    private Long version = 0L;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof VirtualCard)) return false;
        VirtualCard virtualCard = (VirtualCard) o;
        return id != null && id.equals(virtualCard.getId());
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}