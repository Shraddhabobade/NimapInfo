package com.nimap.crudoperations.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nimap.crudoperations.entity.Category;


@Repository
public class CategoryDao {

	@Autowired
	SessionFactory factory;
	
	public String addCategory(Category c) {
		Session session = null;
		Transaction tx = null;
		String msg=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			session.persist(c);
			tx.commit();
			msg="Category added Succesfully";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}
	
	//Update Category
	
	public String updateCategory(Category c) {
	    Session session = null;
	    Transaction tx = null;
	    String msg = null;
	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();

	        Category category = session.get(Category.class, c.getId());
	        if (category != null) {
	            category.setName(c.getName());
	            session.merge(category);
	            tx.commit();
	            msg = "Data Updated successfully";
	        } else {
	            msg = "Category not found";
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return msg;
	}

	//DELETE DATA BY ID
	public String deleteProduct(int id) {
		Session session = null;
		Transaction tx = null;
		String msg=null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			Category c = session.get(Category.class, id);
			session.remove(c);
			
			tx.commit();
			msg="Category Deleted Successfully";
			
		}catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session != null) {
				session.close();
			}
		}return msg;
	}
	
	public Category getCategory(int id) {
		Session session = null;
		Transaction tx = null;
		Category category = null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			category=session.get(Category.class, id);
			tx.commit();
		}catch (Exception e) {
			if(session != null) {
				session.close();
			}
		}
		return category;
	}
	
	public List<Category> getAllCategory(){
		Session session = null;
		Transaction tx = null;
		List<Category> list = null;
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			String hqlQry = "from Category";
			Query<Category> qry = session.createQuery(hqlQry, Category.class);
			list = qry.list();
			tx.commit();
		}catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}
	
	public List<Category> getCategories(int pageNumber, int pageSize) {
	    Session session = null;
	    Transaction tx = null;
	    List<Category> categories = null;
	    
	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();
	        
	        Query<Category> query = session.createQuery("FROM Category", Category.class);
	        query.setFirstResult((pageNumber - 1) * pageSize);
	        query.setMaxResults(pageSize);
	        
	        categories = query.getResultList();
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return categories;
	}
}
