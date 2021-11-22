package com.company;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Patient.
 */
public class Patient extends Person{
    private Date birth;
    private String gender;
    private String baseInsuranceType;
    private String additionalInsuranceType;
    private String educationalDegree;
    private String location;
    private String job;
    /**
     * The Medical history.
     */
    MedicalHistory medicalHistory;
    /**
     * The Reliable medics.
     */
    ArrayList<Medic> reliableMedics;

    /**
     * Instantiates a new Patient.
     *
     * @param name                    the name
     * @param birth                   the birth
     * @param gender                  the gender
     * @param baseInsuranceType       the base insurance type
     * @param additionalInsuranceType the additional insurance type
     * @param educationalDegree       the educational degree
     * @param location                the location
     * @param job                     the job
     * @param medicalHistory          the medical history
     */
    public Patient(String name, Date birth, String gender, String baseInsuranceType, String additionalInsuranceType, String educationalDegree, String location, String job, MedicalHistory medicalHistory) {
        super(name);
        this.birth = birth;
        this.gender = gender;
        this.baseInsuranceType = baseInsuranceType;
        this.additionalInsuranceType = additionalInsuranceType;
        this.educationalDegree = educationalDegree;
        this.location = location;
        this.job = job;
        this.medicalHistory = medicalHistory;
        this.reliableMedics = new ArrayList<>();
    }

    /**
     * Add medic.
     *
     * @param medic the medic
     */
    public void addMedic(Medic medic){
        reliableMedics.add(medic);
    }

    /**
     * Gets medical history.
     *
     * @return the medical history
     */
    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets medical history.
     *
     * @param medicalHistory the medical history
     */
    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
