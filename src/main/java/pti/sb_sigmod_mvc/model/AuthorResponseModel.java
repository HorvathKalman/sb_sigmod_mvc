package pti.sb_sigmod_mvc.model;

public class AuthorResponseModel {

    private String status = "SAVE_FAILED";

    public void setStatusOK() {
        this.status = "SAVE_OK";
    }

    public void setStatusFailed() {
        this.status = "SAVE_FAILED";
    }

    public String getStatus() {
        return status;
    }

}
