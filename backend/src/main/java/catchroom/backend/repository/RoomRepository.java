package catchroom.backend.repository;


import catchroom.backend.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final EntityManager em;

    public void save(Room room) {
        em.persist(room);
    }

    public Room findOne(Integer roomId){
        return em.find(Room.class,roomId);
    }

    public List<Room> findSearch(String search, int offset,int maxResult) {
        return em.createQuery("select r from Room r" +
                        "", Room.class)
//                .setParameter("search", search)
//                .setFirstResult(offset)
//                .setMaxResults(maxResult)
                .getResultList();

    }

    public List<Room> findWish(String email) {
        return em.createQuery("select r from WishRoom w join w.room r" +
                        " join w.member m" +
                        " where m.email = :email",Room.class)
                .setParameter("email",email)
                .getResultList();
    }
}
