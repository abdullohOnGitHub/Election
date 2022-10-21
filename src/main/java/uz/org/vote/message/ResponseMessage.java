package uz.org.vote.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {
    private int statusCode;
    private String message;
    private Object data;

    public static ResponseMessage find(){
        return new ResponseMessage(0,"ok",null);
    }
    public static ResponseMessage find(Object data){
        return new ResponseMessage(0,"ok",data);
    }
    public static ResponseMessage deleted(){
        return new ResponseMessage(0,"deleted",null);
    }

    public static ResponseMessage added(){
        return new ResponseMessage(0,"added",null);
    }

    public static ResponseMessage added(Object data){
        return new ResponseMessage(0,"added",data);
    }

    public static ResponseMessage notFound(){
        return new ResponseMessage(100,"not found",null);
    }

    public static ResponseMessage accessDenied(){
        return new ResponseMessage(101,"access denied",null);
    }

    public static ResponseMessage notSaved(){
        return new ResponseMessage(102,"something went wrong, please check your data",null);
    }

}
