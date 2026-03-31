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
    @Override
    public String toString(){
        return "DataType: "+ this.DataType + "\n" +
                "Data:" + this.Data;
    }
    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

}
