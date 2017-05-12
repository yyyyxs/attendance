package com.jmhz.device.dao.impl;

import com.jmhz.device.Constants;
import com.jmhz.device.dao.AuthBaseDaoI;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.AuthorityUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class AuthBaseDaoImpl<T> implements AuthBaseDaoI<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ServletContext servletContext;


    User user = null;
    /**
     * 获得当前事物的session
     *
     * @return org.hibernate.Session
     */
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        if (o != null) {
            return this.getCurrentSession().save(o);
        }
        return null;
    }

    @Override
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    @Override
    public T get(String hql) {

        /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);

        Map<String, Object> params = new HashMap<String, Object>();
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public T get(String hql, Map<String, Object> params) {

         /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public void delete(T o) {
        if (o != null) {
            this.getCurrentSession().delete(o);
        }
    }

    @Override
    public void update(T o) {
        if (o != null) {
            this.getCurrentSession().update(o);
        }
    }

    @Override
    public void saveOrUpdate(T o) {
        if (o != null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    @Override
    public List<T> find(String hql) {
       /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        Map<String, Object> params = new HashMap<String, Object>();
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {

          /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
       /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<T> find(String hql, int page, int rows) {
           /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        Map<String, Object> params = new HashMap<String, Object>();
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public Long count(String hql) {
        /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        Map<String, Object> params = new HashMap<String, Object>();
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {

           /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);


        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
         /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        Map<String, Object> params = new HashMap<String, Object>();
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {

         /*
        权限控制
         */
        user = (User) servletContext.getAttribute(Constants.CURRENT_USER);
        hql = AuthorityUtils.getAuthoritySql(user, params, hql);

        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.executeUpdate();
    }

    @Override
    public List<Object[]> findBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    @Override
    public List<Object[]> findBySql(String sql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.list();
    }

    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public int executeSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return q.executeUpdate();
    }

    @Override
    public BigInteger countBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public BigInteger countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            //for (String key : params.keySet()) {
            //    q.setParameter(key, params.get(key));
            //}
            for (Map.Entry entry : params.entrySet()) {
                q.setParameter(entry.getKey().toString(), entry.getValue());
            }
        }
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public void merge(T o) {
        this.getCurrentSession().merge(o);
    }

}
