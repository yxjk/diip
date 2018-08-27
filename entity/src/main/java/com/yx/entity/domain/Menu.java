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

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Menu parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Menu> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(nameEn, menu.nameEn) &&
                Objects.equals(parent, menu.parent) &&
                Objects.equals(router, menu.router) &&
                Objects.equals(seq, menu.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, icon, name, nameEn, parent, router, seq);
    }
}
