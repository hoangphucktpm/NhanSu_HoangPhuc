package testdao;


import dao.Impl.PositionImpl;
import dao.PositionDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.rmi.Naming;
import java.rmi.RemoteException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionImplDaoTest {

    private static final String URL = "rmi://HOANGPHUC:6541/";

    private PositionDao positionDao;

    @BeforeAll
    public void setUp() throws RemoteException {
        positionDao = new PositionImpl();
    }

    @Test
    public void testListPosition() throws RemoteException {
        positionDao.listPosition("Senior Software Engineer", 5000, 100000).forEach(x -> System.out.println(x));
    }

    @AfterAll
    public void tearDown() throws RemoteException {
        positionDao = null;
    }
}
