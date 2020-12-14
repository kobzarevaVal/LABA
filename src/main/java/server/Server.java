package server;

import entity.Serial;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements ISerialServer {
    private ArrayList<Serial> serials;

    public Server() throws RemoteException {
        serials = new ArrayList<>();
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            ISerialServer stub = (ISerialServer) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.createRegistry(3034);
            registry.rebind("ISerialServer", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Serial serial) throws RemoteException {
        serials.remove(serial);
    }

    @Override
    public void remove(int index) throws RemoteException {
        serials.remove(index);
    }

    @Override
    public void change(int index, Serial serial) throws RemoteException {
        serials.set(index,serial);
    }

    @Override
    public ArrayList<Serial> getAll() throws RemoteException {
        return serials;
    }

    @Override
    public void add(Serial serial) throws RemoteException {
        serials.add(serial);
    }
}
