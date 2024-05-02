
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
        studentDAO.createTable();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Student student = new Student(0, name, email, phone, address);

        studentDAO.insertStudent(student);

        response.sendRedirect("confirmation.jsp?name=" + name + "&email=" + email + "&phone=" + phone + "&address=" + address);
    }
}
