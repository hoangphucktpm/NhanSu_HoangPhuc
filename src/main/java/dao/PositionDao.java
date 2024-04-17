package dao;

import entity.Position;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PositionDao extends Remote {
    public List<Position> listPosition(String name, double salaryFrom, double salaryTo) throws RemoteException;
}
