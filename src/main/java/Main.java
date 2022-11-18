import com.homework.EmployeeDAO;
import com.homework.EmployeeService;
import com.homework.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        Menu menu = (Menu) context.getBean("menu");
        menu.showMenu();


    }
}
