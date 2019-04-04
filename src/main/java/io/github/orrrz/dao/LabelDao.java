package io.github.orrrz.dao;

import io.github.orrrz.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by icejam.
 * LabelDao
 * 继承JpaRepository，提供简单的CRUD，泛型1为实体类，泛型2为实体id的类型
 */
public interface LabelDao extends JpaRepository<Label, String> {
}
