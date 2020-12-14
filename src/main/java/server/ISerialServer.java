package server;

import entity.Serial;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISerialServer extends Remote {
    // add remove change getAll
    void remove(Serial serial) throws RemoteException;
    void remove(int index) throws RemoteException;
    void change(int index, Serial serial) throws RemoteException;
    ArrayList<Serial> getAll() throws RemoteException;
    void add(Serial serial) throws RemoteException;
}
