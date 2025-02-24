package miun.dt170g.antons;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AlaCarteMenuBean {
    private final String filePath = "styles/Anton.pdf";

    public String getFilePath(){
        return filePath;
    }
}
