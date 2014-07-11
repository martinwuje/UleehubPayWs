package com.uleehub.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * @filename：UUIdEntity.java
 * @author：mkwu
 * @date：2014-3-21 
 * @version：v1.0
 * @Description：
 */
//JPA 基类的标识
@MappedSuperclass
public abstract class UUIdEntity extends AbstractEntity<String> {
	
	private String id;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
