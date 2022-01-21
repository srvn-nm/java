package org.ce.ap.com.company.server.service;


import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class requestJSONHandler{
    /**
     * this method will create a request json object
     * @param methodName
     * @param description
     * @param parameterValues
     * @return result
     */
    public String JSONMaker(String methodName, String description, ArrayList<String> parameterValues){
        String res = "";
        try {
            JSONObject obj = new JSONObject();

            obj.put("methodName:",methodName);
            obj.put("description:",description);
            obj.put("parameterValues:",parameterValues);

            StringWriter out = new StringWriter();
            obj.writeJSONString(out);

            res = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * this method will write the json object to the file
     * @param rjson
     */
    public void writeToFile(String rjson){
        File propertiesFile = new File("./files/log/ClientRequest.txt");

        try(FileWriter fileOutputStream = new FileWriter(propertiesFile,true)){
            String temp = "\r\n";
            fileOutputStream.write(rjson+temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
