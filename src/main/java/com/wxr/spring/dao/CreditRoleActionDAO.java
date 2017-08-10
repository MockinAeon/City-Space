package com.wxr.spring.dao;



import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.Reservation;


public class CreditRoleActionDAO extends DAO {

	public CreditRoleActionDAO() {
	}


	public Reservation processReservation(CreditAdmin creditAdmin, int reservationid, String advice) throws AdException {
		try {
			advice = clean(advice);
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id",reservationid));
			Reservation reservation = (Reservation) crit.uniqueResult();
			reservation.setAdvice(advice);
			reservation.setProcessPerson(creditAdmin.getName());
			reservation.setReservationStatus("Sent Complete");
			getSession().update(reservation);
			commit();
			return reservation;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}
	public Guest addGuestCredit(long accountid) throws AdException {
		try {
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Guest.class);
			crit.add(Restrictions.eq("accountid",accountid));
			Guest guest = (Guest) crit.uniqueResult();
			guest.setCredit(guest.getCredit()+1);
			getSession().update(guest);
			commit();
			return guest;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}
	public Guest reduceGuestCredit(long accountid) throws AdException {
		try {
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Guest.class);
			crit.add(Restrictions.eq("accountid",accountid));
			Guest guest = (Guest) crit.uniqueResult();
			guest.setCredit(guest.getCredit()-1);
			getSession().update(guest);
			commit();
			return guest;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}
	private String clean(String value) {
        value = value.replaceAll("<", "").replaceAll(">", "");
        value = value.replaceAll("\\(", "").replaceAll("\\)", "");
        value = value.replaceAll("'", "");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "");
        value = value.replaceAll("script", "");
        return value;
    }
}