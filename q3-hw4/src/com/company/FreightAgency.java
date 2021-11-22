package com.company;

import java.util.ArrayList;

public class FreightAgency {
    //keeps cargoes
    ArrayList<Cargo> cargoes;
    //keeps Clients
    ArrayList<Client> clients;

    /**
     * Create a new FreightAgency
     */
    public FreightAgency() {
        cargoes = new ArrayList<>();
        clients=new ArrayList<>();
    }

    /**
     * Add Cargo to CargoList
     *
     * @param cargo we want to add
     */
    public void addCargo(Cargo cargo) {
        if (!cargoes.contains(cargo))
            cargoes.add(cargo);
        else
            System.out.println("cargo already exists!");
    }

    /**
     * Remove a cargo from CargoList
     *
     * @param cargo we want to add
     */
    public void removeCargo(Cargo cargo) {
        if (cargoes.contains(cargo))
            cargoes.remove(cargo);
        else
            System.out.println("cargo doesn't exist!");
    }

    /**
     * Show list of cargoes
     */
    public void showCargoList() {
        if (cargoes.size() == 0)
            System.out.println("No cargo added yet");
        else {
            int i = 1;
            for (Cargo c : cargoes) {
                System.out.println(i + ": " + c.toString());
                i++;
            }
        }
    }

    /**
     * Add client to client list
     * @param client
     */
    public void addClients(Client client){
        boolean test = true;
        for (Client c:clients) {
            if(c.toString().equals(client.toString()))
                test = false;
        }
        if (test) {
            clients.add(client);
            System.out.println("Client registered!");
        }
        else
            System.out.println("Client has already registered!");
    }

    /**
     * Remove a client from client list
     * @param client
     */
    public void removeClient(Client client) {
        if (clients.contains(client))
            clients.remove(client);
        else
            System.out.println("client doesn't exist!");
    }
    /**
     * Show list of clients
     */
    public void showClientList() {
        if (clients.size() == 0)
            System.out.println("No client added yet");
        else {
            int i = 1;
            for (Client c : clients) {
                System.out.println(i + ": " + c.toString());
                i++;
            }
        }
    }
}