import com.homework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "com.homework")
@PropertySource("classpath:${env}-application.properties")


public class AppConfig {

    @Autowired
    Environment env;

    @Bean (name = "employeeDAO")
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAO();
    }

    @Bean (name = "DBconnector")
    public DBConnector dbConnector () throws SQLException {
        return new DBConnector(env.getProperty("driver"),env.getProperty("dbUrl"),
                env.getProperty("DbUserName"), env.getProperty("DbPassword"), env.getProperty("schema"));
    }

    @Bean (name = "employeeService")
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

    @Bean (name = "employeeDataCollector")
    public EmployeeDataCollector employeeDataCollector() {
        return new EmployeeDataCollector();
    }







}
