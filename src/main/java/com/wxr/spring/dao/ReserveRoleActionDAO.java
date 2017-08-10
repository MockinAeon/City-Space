package com.wxr.spring.dao;



import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.Reservation;
import com.wxr.spring.pojo.ReserveAdmin;


public class ReserveRoleActionDAO extends DAO {

	public ReserveRoleActionDAO() {
	}

	public Reservation rejectReservation( ReserveAdmin reserveAdmin, int reservationid, String reason) throws AdException {
		try {
			reason = clean(reason);
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id",reservationid));
			Reservation reservation = (Reservation) crit.uniqueResult();
			reservation.setRejectedReason(reason);;
			reservation.setProcessPerson(reserveAdmin.getName());
			reservation.setReservationStatus("Rejected");
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

	public Reservation approveReservation( ReserveAdmin reserveAdmin, int reservationid) throws AdException {
		try {
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id",reservationid));
			Reservation reservation = (Reservation) crit.uniqueResult();
			reservation.setProcessPerson(reserveAdmin.getName());
			reservation.setReservationStatus("Approve");
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