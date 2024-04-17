package dao.Impl;

import dao.CadidateDao;
import dao.PositionDao;
import entity.Candidate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CadidateImpl extends UnicastRemoteObject implements CadidateDao {

    private static final long serialVersionUID = 1L;
    private EntityManager em;

    public CadidateImpl() throws RemoteException {
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    //b. Liet ke danh sách các ứng viên và số công ty mà các ứng viên này từng làm
    // listCadidatesByCompanies(): Map<Cadidate, Long>
    public Map<Candidate, Long> listCadidatesByCompanies() throws RemoteException {
        return em.createNamedQuery("Candidate.listCadidatesByCompanies", Object[].class)
                .getResultList().stream()
                .collect(Collectors.toMap(
                        e -> (Candidate) e[0],
                        e -> (Long) e[1]
                ));
    }

    //c. Tim danh sach cac ung vien da lam viec tren mot vị tri cong viec nao do có thoi gian lam lau nhat
    // listCadidatesWithLongestWorking(): Map<Cadidate, Long>
    public Map<Candidate, Long> listCadidatesWithLongestWorking() throws RemoteException {
        List<Object[]> results = em.createNamedQuery("Candidate.listCadidatesWithLongestWorking", Object[].class)
                .getResultList();

        Map<Candidate, Long> resultMap = new HashMap<>();
        Long maxWorkingTime = null;
        for (Object[] result : results) {
            if (result[0] instanceof Candidate && result[1] instanceof Number) {
                Long workingTime = ((Number) result[1]).longValue();
                resultMap.put((Candidate) result[0], workingTime);
                if (maxWorkingTime == null || workingTime > maxWorkingTime) {
                    maxWorkingTime = workingTime;
                }
            }
        }

        final Long finalMaxWorkingTime = maxWorkingTime;
        resultMap = resultMap.entrySet().stream()
                .filter(e -> e.getValue().equals(finalMaxWorkingTime))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return resultMap;
    }

}
