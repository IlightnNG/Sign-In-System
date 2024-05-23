package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.classes.Envelope;
import org.example.classes.InfoString;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonControl {
    // Json 和 类 互转
    public static String infoToJson(InfoString infoString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(infoString);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  new String();
    }

    public static InfoString jsonToInfo(String infoJson)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(infoJson, InfoString.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return new InfoString();
    }

    public static String envelopeToJson(Envelope envelope) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(envelope);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  new String();
    }

    public static Envelope jsonToEnvelope(String envelopeString)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(envelopeString, Envelope.class);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return new Envelope();
    }
}
