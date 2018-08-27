package com.yx.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "DI_MENU")
public class Menu {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NAME_EN")
    private String nameEn;
    @Column(name = "ROUTER")
    private String router;
    @Column(name = "SEQ")
    private String seq;
    @Column(name = "PARENT_ID")
    private String parentId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    private List<Menu> children;
}
