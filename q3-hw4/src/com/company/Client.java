package com.company;

import java.util.*;

public class Client {
    //first name of client
    public String FirstName;
    //last name of client
    public String LastName;
    //list of client cargoes
    public HashMap<String,Cargo> cargoes;

    public Client(String fn,String ln){
        this.FirstName = fn;
        this.LastName = ln;
        cargoes=new HashMap<>();
    }

    /**
     * Add Cargo to CargoList
     *
     * @param cargo we want to add
     */
    public void addCargo(Cargo cargo) {
        if (!cargoes.containsValue(cargo)) {
            int max = 1;
            for (String s:cargoes.keySet()) {
                char[] c = s.toCharArray();
                if (Character.getNumericValue(c[5]) > max)
                    max = Character.getNumericValue(c[5]);
            }
            cargoes.put("cargo"+(max+1),cargo);
        }
        else
            System.out.println("cargo already exists!");
    }

    /**
     * Remove a cargo from CargoList
     *
     * @param cargo we want to add
     */
    public void removeCargo(Cargo cargo) {
        Iterator<Map.Entry<String,Cargo>> it = cargoes.entrySet().iterator();
        boolean test = false;
        while (it.hasNext()){
            Map.Entry<String,Cargo> entry = it.next();
            if (cargo.equals(entry.getValue())) {
                test = true;
                it.remove();
            }
        }
        if(!test)
            System.out.println("cargo doesn't exist!");
    }

    /**
     * Show list of cargoes
     */
    public void showCargoList() {
        if (cargoes.size() == 0)
            System.out.println("No cargo set yet");
        else {
            int i = 1;
            for (String s : cargoes.keySet()) {
                System.out.println(i + ": " + s);
                i++;
            }
        }
    }
    @Override
    public String toString(){
        return this.FirstName+" "+this.LastName;
    }
}