package start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        // error 남
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();

        member.setId(id);
        member.setUsername("유진");
        member.setAge(20);

        // 등록
        em.persist(member);

        // 수정
        member.setAge(30);

        // 조회
        Member findMember = em.find(Member.class, id);
        System.out.println(findMember);
    }
}
