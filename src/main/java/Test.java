import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by KoRsaR on 28.06.17.
 */
@ManagedBean(name = "test")
@ViewScoped
public class Test implements Serializable{

    private String name = "test";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
