package com.nimap.crudoperations.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.nimap.crudoperations.entity.Category;
import com.nimap.crudoperations.entity.Product;





@Repository
public class ProductDao {

	@Autowired
	SessionFactory factory;
	
	//ADD DATA INTO PRODUCT
	public String addProduct(Product p) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			session.persist(p);
			tx.commit();
			msg = "Product Addedd Succesfully..";

		} catch (Exception e) {
			// TODO: handle exception
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
	
	//UPDATE DATA
	public String updateProduct(Product p) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Product product = session.get(Product.class, p.getId());

			product.setName(p.getName());
			session.merge(product);
			tx.commit();
			msg = "Data Updated Succefully..";

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
			
			Product p = session.get(Product.class, id);
			session.remove(p);
			
			tx.commit();
			msg="Product Deleted Successfully";
			
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
	
	//GET DATA BY ID
	public Product getProductById(int id) {
		Session session = null;
		Transaction tx = null;
		Product product = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			product = session.get(Product.class, id);
			tx.commit();
		}catch (Exception e) {
			if(session != null) {
				session.close();
			}
		}
		return product;
	}
	
	//GET ALL RECORDS
	public List<Product> getAllProduct(){
		Session session = null;
		Transaction tx = null;
		
		List<Product> list = null;
		
		try {
			session = factory.openSession();
			tx=session.beginTransaction();
			
			String hqlQry = "from Product";
			Query<Product> qry = session.createQuery(hqlQry, Product.class);
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
	
	public List<Product> getProducts(int pageNumber, int pageSize) {
	    Session session = null;
	    Transaction tx = null;
	    List<Product> product = null;
	    
	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();
	        
	        Query<Product> query = session.createQuery("FROM Product", Product.class);
	        query.setFirstResult((pageNumber - 1) * pageSize);
	        query.setMaxResults(pageSize);
	        
	        product = query.getResultList();
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
	    
	    return product;
	}
}
