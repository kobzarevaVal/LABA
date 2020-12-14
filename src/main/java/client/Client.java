package client;

import entity.Serial;
import server.ISerialServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry(3034);
            ISerialServer stub = (ISerialServer) registry.lookup("ISerialServer");
            stub.add(new Serial("name1",3,2.3f));
            stub.add(new Serial("name2",6,5.3f));
            stub.add(new Serial("name3",7,6.3f));
            stub.add(new Serial("name4",8,7.3f));
            stub.add(new Serial("name5",9,9.3f));
            System.out.println(stub.getAll());
            stub.change(2,new Serial());
            System.out.println(stub.getAll());
            stub.remove(0);
            System.out.println(stub.getAll());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
