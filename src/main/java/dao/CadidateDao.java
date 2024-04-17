package dao;

import entity.Candidate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface CadidateDao extends Remote {
    public Map<Candidate, Long> listCadidatesByCompanies() throws RemoteException;
    public Map<Candidate, Long> listCadidatesWithLongestWorking() throws RemoteException;
}
