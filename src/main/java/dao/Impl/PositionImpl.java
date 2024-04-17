package dao.Impl;

import dao.PositionDao;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class PositionImpl extends UnicastRemoteObject implements PositionDao {
    
    private static final long serialVersionUID = 1L;
    private EntityManager em;
    
    public PositionImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }
    
    //a.Liet ke danh sach các vị tri cong viec khi biet ten vi tri (tìm tương doi)
    // va muc luong khoang tu, ket qua sap xep theo vi tri cong viec
    // listPosition(String name, double salaryFrom, double salaryTo): List<Position>

    public List<Position> listPosition(String name, double salaryFrom, double salaryTo) throws RemoteException {
        return em.createNamedQuery("Position.listPosition", Position.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .getResultList();
    }

}
