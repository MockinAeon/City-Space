package com.wxr.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Guest;

public class GuestDAO extends DAO {

    public GuestDAO() {
    }

    public Guest getGuest(String name)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Guest where name = :guestname");
            q.setString("guestname", name);
            Guest guest = (Guest) q.uniqueResult();
            commit();
            return guest;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + name, e);
        }finally {
			close();
		}
    }
    public Guest getGuestByAccountId(long id)
            throws AdException {
    	System.out.println("getGuestByID step1");
        try {
        	System.out.println("getGuestByID step2");
            begin();
            System.out.println("getGuestByID step3");
            Query q = getSession().createQuery("from Guest where accountid = :accountid");
            q.setLong("accountid", id);
            System.out.println("getGuestByID step4");
            Guest guest = (Guest) q.uniqueResult();
            commit();
            System.out.println("getGuestByID step5");
            return guest;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + id, e);
        }finally {
			close();
		}
    }
    

    public Guest create(String username, String password, String fullname)
            throws AdException {
    	 try {
             begin();
             System.out.println("inside DAO");
             Guest guest = new Guest();
             guest.setGuestname(fullname);
             guest.setUsername(username);
             guest.setPassword(password);
             guest.setRole("guest");
             getSession().save(guest);
             
             commit();
             return guest;
         } catch (HibernateException e) {
             rollback();
             //throw new AdException("Could not create user " + username, e);
             throw new AdException("Exception while creating user: " + e.getMessage());
         }finally {
 			close();
 		}

    }

}