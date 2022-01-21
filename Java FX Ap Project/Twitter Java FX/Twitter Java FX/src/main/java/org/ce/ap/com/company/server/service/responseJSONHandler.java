package org.ce.ap.com.company.server.service;

import org.ce.ap.com.company.server.model.Errortype;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class responseJSONHandler{
    /**
     * this method will make response json object
     * @param hasError
     * @param error
     * @param count
     * @param result
     * @return result
     */
    public String JSONMaker(boolean hasError, Errortype error, int count, ArrayList<String> result){
        String res = "";
        try {
            JSONObject obj = new JSONObject();

            obj.put("hasError:",hasError);
            obj.put("errorCode",error.toString());
            obj.put("count:", count);
            obj.put("parameterValues:",result);

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
        File propertiesFile = new File("./files/log/ServerResponse.txt");

        try(FileWriter fileOutputStream = new FileWriter(propertiesFile,true)){
            String temp = "\r\n";
            fileOutputStream.write(rjson+temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
