package com.wxr.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.wxr.spring.exception.AdException;
import com.wxr.spring.pojo.Account;
import com.wxr.spring.pojo.City;
import com.wxr.spring.pojo.CreditAdmin;
import com.wxr.spring.pojo.Enterprise;
import com.wxr.spring.pojo.Guest;
import com.wxr.spring.pojo.ReserveAdmin;

public class AccountDAO extends DAO {
	public AccountDAO() {
		// TODO Auto-generated constructor stub
	}

	public Account checkAccount(String username) throws AdException {
		System.out.println("checkaccount step1");

		try {
			System.out.println("checkaccount step2");
			begin();
			System.out.println("checkaccount step3");
			Query q = getSession().createQuery("from Account where username = :username");
			System.out.println("checkaccount step4");

			q.setString("username", username);
			Account account = (Account) q.uniqueResult();
			commit();

			System.out.println("checkaccount step5");
			return account;
		} catch (HibernateException e) {
			rollback();
			System.out.println("checkaccount step6");
			throw new AdException("Could not get user " + username, e);
		} finally {
			close();
		}

	}

	public Account Sysadmin() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			Account account = new Account();
			account.setUsername("sysadmin");
			account.setPassword("sysadmin");
			account.setRole("sysadmin");

			getSession().save(account);

			commit();
			return account;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + account, e);
			throw new AdException("Exception while creating sysadmin: " + e.getMessage());
		} finally {
			close();
		}

	}

	public ReserveAdmin createReserveAdmin(String username, String password, String fullname, Enterprise enterprisename)
			throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			ReserveAdmin reserveAdmin = new ReserveAdmin();
			reserveAdmin.setName(fullname);
			reserveAdmin.setUsername(username);
			reserveAdmin.setPassword(password);
			reserveAdmin.setEnterprisename(enterprisename);
			reserveAdmin.setRole("reserve");

			getSession().save(reserveAdmin);

			commit();
			return reserveAdmin;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}

	}

	// change password
		public void changePassword(long accountid,String password) throws AdException {
			try {
				begin();
				System.out.println("inside DAO");

				Criteria crit = getSession().createCriteria(Account.class);
				crit.add(Restrictions.eq("accountid",accountid));
				Account account =(Account) crit.uniqueResult();
				account.setPassword(password);
				getSession().update(account);
				commit();
			} catch (HibernateException e) {
				rollback();
				// throw new AdException("Could not create user " + username, e);
				throw new AdException("Exception while creating user: " + e.getMessage());
			} finally {
				close();
			}
		}
		
	// list reserveadmin account
	public List getReserveAdmin() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(ReserveAdmin.class);
			List<ReserveAdmin> raList = crit.list();
			commit();
			return raList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	// credit admin account
	public CreditAdmin createCreditAdmin(String username, String password, String fullname, Enterprise enterprisename)
			throws AdException {
		try {
			begin();
			System.out.println("inside DAO");
			CreditAdmin creditAdmin = new CreditAdmin();
			creditAdmin.setName(fullname);
			creditAdmin.setUsername(username);
			creditAdmin.setPassword(password);
			creditAdmin.setEnterprisename(enterprisename);
			creditAdmin.setRole("credit");

			getSession().save(creditAdmin);

			commit();
			return creditAdmin;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}

	}

	// list credit admin account
	public List getCreditAdmin() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(CreditAdmin.class);
			List<CreditAdmin> raList = crit.list();
			commit();
			return raList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}

	// list guest account
	public List getGuest() throws AdException {
		try {
			begin();
			System.out.println("inside DAO");

			Criteria crit = getSession().createCriteria(Guest.class);
			List<Guest> guestList = crit.list();
			commit();
			return guestList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		} finally {
			close();
		}
	}
}
