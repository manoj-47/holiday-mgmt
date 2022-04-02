package io.github.learnjakartaee;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PersistenceLayer {

//    public EntityManager entityManager(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(employee);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//    }

   static Connection con;

    public static Connection getConnection() {
        try{
            if(con == null || con.isClosed()){
                initDS();
            }
        }catch (NamingException | SQLException e){
            System.err.print(""+e.getMessage());
        }
        return  con;
    }

    private static void initDS()  throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/myDataSource");
        con = ds.getConnection();
    }

}
