package Response;

import com.google.gson.annotations.Expose;

public class Response {
    @Expose
    String DataType;
    @Expose
    Object Data;
    public Response(String dt, Object d){
        this.DataType = dt;
        this.Data = d;
    }
    public String getDataType() {
        return DataType;
    }

    public Object getData() {
        return Data;
    }
}
