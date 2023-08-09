import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Methods Invoked");

        ServletInputStream inputStream = req.getInputStream();
       /* int read = inputStream.read();
        System.out.println((char) read);

        int read1 = inputStream.read();
        System.out.println((char) read1);*/

        /*int read;
        while ((read=inputStream.read())!=-1){
            System.out.println(read);
        }*/


        JsonReader reader = Json.createReader(req.getReader());
        JsonStructure jsonObject = reader.read();


    }
}
