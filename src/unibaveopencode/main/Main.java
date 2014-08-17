/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unibaveopencode.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import unibaveopencode.model.vo.AutorVO;

/**
 *
 * @author Stéfani
 */
public class Main {

    /**
     * @param args the command line arguments
     */
   // public static void main(String[] args) {
        // TODO code application logic here
        // LivroVO.builder().codEditora(1).build();

      /*  EntityManagerFactory emf = Persistence.createEntityManagerFactory("uocPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            AutorVO autor = new AutorVO();
            autor.setNomAutor("teste");
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            emf.close();
        }*/
    }
//}
