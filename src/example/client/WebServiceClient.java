package example.client;



import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.client.Call;
import org.apache.commons.io.FileUtils;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

public class WebServiceClient {

    private String url="http://localhost:8080/demo/services/HelloWorld";
    private String namespace="http://example";
    private String method="sayHelloWorldFrom";

    public static void main(String[] args) {
        new WebServiceClient().send();
    }

    public void send(){
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            call.setOperationName(this.getQname());
            call.setTimeout(60000);
            call.setEncodingStyle("utf-8");
            call.setUseSOAPAction(true);
            call.addParameter("from",XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            Object invoke = call.invoke(new Object[]{"houk"});
            System.out.println(invoke);
            String result = (String)invoke;
            File file  = new File("/Users/xuxiongli/Downloads/temp.vim");
            FileUtils.writeByteArrayToFile(file,result.getBytes());
        } catch (ServiceException | RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QName getQname(){
        return new QName(namespace,method);
    }
}
