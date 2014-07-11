/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.uleehub.common.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.uleehub.common.entity.AbstractEntity;
import com.uleehub.common.entity.search.Searchable;
import com.uleehub.common.repository.BaseRepository;

/**
 * <p>抽象service层基类 提供一些简便方法
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 * <p>User: mtwu
 * <p>Date: 13-1-12 下午4:43
 * <p>Version: 1.0
 */
public abstract class BaseService<M extends AbstractEntity, ID extends Serializable> {

    protected BaseRepository<M, ID> baseRepository;
    
    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    /**
     * 保存单个实体
     *
     * @param m 实体
     * @return 返回保存的实体
     */
    public M save(M m) {
        return baseRepository.save(m);
    }

    public M saveAndFlush(M m) {
        m = save(m);
        baseRepository.flush();
        return m;
    }

    /**
     * 更新单个实体
     *
     * @param m 实体
     * @return 返回更新的实体
     */
    public M update(M m) {
        return baseRepository.save(m);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    /**
     * 删除实体
     *
     * @param m 实体
     */
    public void delete(M m) {
        baseRepository.delete(m);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param ids 实体
     */
    public void delete(ID[] ids) {
        baseRepository.delete(ids);
    }


    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public M findOne(ID id) {
        return baseRepository.findOne(id);
    }

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    public boolean exists(ID id) {
        return baseRepository.exists(id);
    }

    /**
     * 统计实体总数
     *
     * @return 实体总数
     */
    public long count() {
        return baseRepository.count();
    }

    /**
     * 查询所有实体
     *
     * @return
     */
    public List<M> findAll() {
        return baseRepository.findAll();
    }

    /**
     * 按照顺序查询所有实体
     *
     * @param sort
     * @return
     */
    public List<M> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    /**
     * 分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return
     */
    public Page<M> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * 按条件分页并排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public Page<M> findAll(Searchable searchable) {
        return baseRepository.findAll(searchable);
    }

    /**
     * 按条件不分页不排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllWithNoPageNoSort(Searchable searchable) {
        searchable.removePageable();
        searchable.removeSort();
        return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
    }

    /**
     * 按条件排序查询实体(不分页)
     *
     * @param searchable 条件
     * @return
     */
    public List<M> findAllWithSort(Searchable searchable) {
        searchable.removePageable();
        return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
    }


    /**
     * 按条件分页并排序统计实体数量
     *
     * @param searchable 条件
     * @return
     */
    public Long count(Searchable searchable) {
        return baseRepository.count(searchable);
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 
	 * @param jpql
	 * @param clazz
	 * @return
	 */
	public <M> List<M> createQuery(String jpql, Class<M> clazz){
		TypedQuery<M> query = entityManager.createQuery(jpql, clazz);
		return query.getResultList();
	}
	
	/**
	 * 
	 * @param String jpql
	 * @param Map<String, Object>params
	 * @param clazz
	 * @return
	 */
	public <M> List<M> createQuery(String jpql, Map<String, Object> params, Class<M> clazz){
		TypedQuery<M> query = entityManager.createQuery(jpql, clazz);
		buildQueryParams(query, params);
		return query.getResultList();
	}
	
	/**
	 * 根据@NamedNativeQuery 或 @NamedQuery 查询
	 * @param queryName
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List createNamedQuery(String queryName,Map<String, Object> params){
		Query query = entityManager.createNamedQuery(queryName);	
		buildQueryParams(query, params);
		return query.getResultList();
	}
	/**
	 * 原生态SQL查询，返回List<Map<String, Object>>
	 * @param sql
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> createNativeQueryToMap(String sql){
		Query query = entityManager.createNativeQuery(sql);  
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
	private void buildQueryParams(Query query, Map<String, Object> params){
		if(null != params){
			Iterator<String> iterator= params.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				query.setParameter(key, params.get(key));
			}
		}
	}
}
