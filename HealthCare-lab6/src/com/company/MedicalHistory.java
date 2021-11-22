package com.company;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Medical history.
 */
public class MedicalHistory {
    private Illnesstype illnesstype;
    private String illnessSituation;
    private String medicDetection;
    private ArrayList<String> prescriptedDrugs;
    private IllnessSecurityLevel illnessSecurityLevel;
    private Date date;

    /**
     * Instantiates a new Medical history.
     *
     * @param illnesstype          the illnesstype
     * @param illnessSituation     the illness situation
     * @param medicDetection       the medic detection
     * @param prescriptedDrugs     the prescripted drugs
     * @param illnessSecurityLevel the illness security level
     */
    public MedicalHistory(Illnesstype illnesstype, String illnessSituation, String medicDetection, ArrayList<String> prescriptedDrugs, IllnessSecurityLevel illnessSecurityLevel) {
        this.illnesstype = illnesstype;
        this.illnessSituation = illnessSituation;
        this.medicDetection = medicDetection;
        this.prescriptedDrugs = prescriptedDrugs;
        this.illnessSecurityLevel = illnessSecurityLevel;
    }
}
