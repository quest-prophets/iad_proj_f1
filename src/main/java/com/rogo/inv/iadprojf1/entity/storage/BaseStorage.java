package com.rogo.inv.iadprojf1.entity.storage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogo.inv.iadprojf1.entity.Team;
import com.rogo.inv.iadprojf1.entity.AcceptStatus;
import com.rogo.inv.iadprojf1.entity.ComponentCondition;
import com.rogo.inv.iadprojf1.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter @ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseStorage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ToString.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @JsonBackReference
    protected Team team;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected ComponentCondition condition;

    @NotNull @Min(0)
    protected double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected AcceptStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender")
    private User sender;

    @Column(name = "comment")
    private String comment;

    /* ================================
     constructors
    ================================ */
    public BaseStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) double price, @NotNull AcceptStatus status) {
        this.team = team;
        this.condition = condition;
        this.price = price;
        this.status = status;
    }

    public BaseStorage(@NotNull Team team, @NotNull ComponentCondition condition, @NotNull @Min(0) double price, @NotNull AcceptStatus status, User sender) {
        this.team = team;
        this.condition = condition;
        this.price = price;
        this.status = status;
        this.sender = sender;
    }
}
