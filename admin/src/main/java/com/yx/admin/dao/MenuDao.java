package com.yx.admin.dao;

import com.yx.entity.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, String> {

    public List<Menu> findByParentId(String parentId);
}
