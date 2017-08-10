package com.wxr.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Reservation;

public class GetReservationDAO extends DAO {
	public GetReservationDAO() {
		// TODO Auto-generated constructor stub
	}

	public List creditRoleGetReservation(Enterprise enterprise) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Reservation.class);
			Criterion checkenterprise = Restrictions.eq("enterprisename", enterprise);
			Criterion checksent = Restrictions.eq("reservationStatus", "Sent");
			LogicalExpression andExp = Restrictions.and(checkenterprise, checksent);
			crit.add(andExp);
			List<Reservation> reservationList = crit.list();
			commit();
			return reservationList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}
	
	public List reserveRoleGetReservation(Enterprise enterprise) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Reservation.class);
			Criterion checkenterprise = Restrictions.eq("enterprisename", enterprise);
			Criterion checksent = Restrictions.like("reservationStatus", "Sent%");
			LogicalExpression andExp = Restrictions.and(checkenterprise, checksent);
			crit.add(andExp);
			List<Reservation> reservationList = crit.list();
			commit();
			return reservationList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}finally {
			close();
		}
	}
}
