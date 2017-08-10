package com.wxr.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Account;
import com.wxr.spring.pojo.City;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.ReserveAdmin;

public class GetAccountDAO extends DAO {
	public GetAccountDAO() {
		// TODO Auto-generated constructor stub
	}

	public CreditAdmin getCreditByAccountId(long id)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from CreditAdmin where accountid = :accountid");
            q.setLong("accountid", id);
            CreditAdmin creditAdmin = (CreditAdmin) q.uniqueResult();
            commit();
            System.out.println("getCreditByAccountId step5");
            return creditAdmin;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + id, e);
        }finally {
			close();
		}
    }
	public ReserveAdmin getReserveByAccountId(long id)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from ReserveAdmin where accountid = :accountid");
            q.setLong("accountid", id);
            ReserveAdmin reserveAdmin = (ReserveAdmin) q.uniqueResult();
            commit();
            System.out.println("getCreditByAccountId step5");
            return reserveAdmin;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + id, e);
        }finally {
			close();
		}
    }
}
