package pti.sb_sigmod_mvc.dto;

public class LoadRequestDTO {
    private String loadType;
    private String xmlPath;

    public LoadRequestDTO(String loadType, String xmlPath) {
        this.loadType = loadType;
        this.xmlPath = xmlPath;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }
}
