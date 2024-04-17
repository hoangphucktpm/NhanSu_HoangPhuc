package testdao;


import dao.CadidateDao;
import dao.Impl.CadidateImpl;
import dao.Impl.PositionImpl;
import dao.PositionDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.rmi.Naming;
import java.rmi.RemoteException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CandidateDaoTest {

    private static final String URL = "rmi://HOANGPHUC:6541/";

    private CadidateDao candidateDao;

    @BeforeAll
    public void setUp() throws RemoteException {
        candidateDao = new CadidateImpl();
    }

    @Test
    public void testListCadidatesByCompanies() throws RemoteException {
        candidateDao.listCadidatesByCompanies().forEach((k, v) -> System.out.println(k + " soluong:" + v));
    }

    @Test
    public void testListCadidatesWithLongestWorking() throws RemoteException {
        // tìm ra danh sách ứng viên có thời gian làm việc lâu nhất
        candidateDao.listCadidatesWithLongestWorking().forEach((k, v) -> System.out.println(k + " thoi gian lam viec:" + v));
    }

    @AfterAll
    public void tearDown() throws RemoteException {
        candidateDao = null;
    }
}
