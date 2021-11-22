package com.company;

public class Medic extends Person {
    /**
     * cunstructor for method
     *
     * @param name
     */
    public Medic(String name) {
        super(name);
    }

    /**
     * this method helps a medic to have acces to a patients medical history
     *
     * @param patient
     */
    public void accessHistory(Patient patient) {

    }

    /**
     * the medic can change its patients medic and send him/her to anothe medic to be diagnosed
     * using this method
     *
     * @param patient
     * @param medic
     */
    public void changeMedic(Patient patient, Medic medic) {

    }

    /**
     * with this method,the medic can prescribe drugs for his/her patient
     *
     * @param patient
     */
    public void prescription(Patient patient) {

    }
}
