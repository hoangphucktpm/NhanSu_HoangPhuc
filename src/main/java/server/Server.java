package server;


import dao.CadidateDao;
import dao.Impl.CadidateImpl;
import dao.Impl.PositionImpl;
import dao.PositionDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Server {

    private static final String URL = "rmi://HOANGPHUC:6541/";

    public static void main(String[] args) {
        try {
            Context context = new InitialContext();

            PositionDao positionDao = new PositionImpl();
            CadidateDao candidateDao = new CadidateImpl();

            LocateRegistry.createRegistry(6541);

            context.rebind(URL + "positionDao", positionDao);
            context.rebind(URL + "candidateDao", candidateDao);

            System.out.println("Server is running...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
