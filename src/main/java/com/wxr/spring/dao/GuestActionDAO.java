package com.wxr.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.Reservation;
import com.wxr.spring.pojo.Space;
import com.wxr.spring.pojo.Subscription;

public class GuestActionDAO extends DAO {

	public GuestActionDAO() {
	}

	// guest.setUsingSpace(space);
	// space.setSpaceStatus("");
	public String useSpace(Guest guest, Space space) throws AdException {
		try {
			begin();
			System.out.println("SpaceAction useSpace inside");
			System.out.println("SpaceAction using space:" + space);
			space.setSpaceStatus("Using");
			guest.setUsingSpace(space);
			Session session = getSession();
			session.update(space);
			session.update(guest);
			commit();
			return "success";
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public String leaveSpace(Guest guest, Space space) throws AdException {
		try {
			System.out.println("SpaceAction useSpace inside");
			begin();
			guest.setUsingSpace(null);
			space.setSpaceStatus("Empty");
			Session session = getSession();
			session.update(space);
			session.update(guest);
			commit();
			return "success";
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public Subscription createSubcribe(Guest guest, Space space) throws AdException {
		try {
			System.out.println("SpaceAction subcribe inside");
			begin();
			Subscription subscription = new Subscription();
			subscription.setAccountid(guest);
			subscription.setSpacename(space);
			getSession().save(subscription);
			commit();
			return subscription;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public Subscription checkSubcribe(Guest guest, Space space) throws AdException {
		try {
			System.out.println("SpaceAction subcribe inside");
			begin();
			Criteria crit = getSession().createCriteria(Subscription.class);
			Criterion checkspace = Restrictions.eq("spacename", space);
			Criterion checkguest = Restrictions.eq("accountid", guest);
			LogicalExpression andExp = Restrictions.and(checkguest, checkspace);
			crit.add(andExp);
			Subscription subscription = (Subscription) crit.uniqueResult();
			commit();
			return subscription;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public Reservation createReservation(Space space, Guest accountid, Enterprise enterprise, String reserveday,
			String starttime, String endtime) throws AdException {
		try {
			System.out.println("SpaceAction createReservation inside");
			begin();
			Reservation reservation = new Reservation();
			reservation.setAccountid(accountid);
			reservation.setSpacename(space);
			reservation.setEnterprisename(enterprise);
			reservation.setReserveDay(reserveday);
			reservation.setStartTime(starttime);
			reservation.setEndTime(endtime);
			reservation.setReservationStatus("Sent");
			getSession().save(reservation);
			commit();
			return reservation;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public String addReservationTimes(Guest guest, int times) throws AdException {
		try {
			System.out.println("SpaceAction createReservation inside");
			begin();
			System.out.println("addReservationTimes Action, New Times" + times);
			guest.setReservationTimes(times);
			getSession().update(guest);
			commit();
			return "success";
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public List getReservation(Guest guest) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("accountid", guest));
			List<Reservation> reservationList = crit.list();
			commit();
			return reservationList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List getSubscription(Guest guest) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Subscription.class);
			crit.add(Restrictions.eq("accountid", guest));
			List<Subscription> subscriptionList = crit.list();
			commit();
			return subscriptionList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	public void deleteSubscription(int subscriptionid) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Subscription.class);
			crit.add(Restrictions.eq("id", subscriptionid));
			Subscription subscription = (Subscription) crit.uniqueResult();
			getSession().delete(subscription);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	public void cancelReservation(int reservationid, Guest guest, String reason) throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id", reservationid));
			Reservation reservation = (Reservation) crit.uniqueResult();
			reservation.setReservationStatus("Cancel");
			reservation.setCancelReason(reason);
			// guest.setCancelTimes(guest.getCancelTimes()+1);
			Session session = getSession();
			// session.update(guest);
			session.update(reservation);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	public void addCancelTimes(Guest guest, int times) throws AdException {
		try {
			System.out.println("SpaceAction createReservation inside");
			begin();
			System.out.println("addReservationTimes Action, New Times" + times);
			guest.setCancelTimes(times);
			getSession().update(guest);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}

	public void updateReservation(int reservationid, String reserveday, String starttime, String endtime)
			throws AdException {
		try {
			System.out.println("SpaceAction createReservation inside");
			begin();
			Criteria crit = getSession().createCriteria(Reservation.class);
			crit.add(Restrictions.eq("id", reservationid));
			Reservation reservation = (Reservation) crit.uniqueResult();
			reservation.setReservationStatus("Sent");
			reservation.setReserveDay(reserveday);
			reservation.setStartTime(starttime);
			reservation.setEndTime(endtime);
			reservation.setProcessPerson(null);
			getSession().update(reservation);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get space " + e);
		} finally {
			close();
		}

	}
}