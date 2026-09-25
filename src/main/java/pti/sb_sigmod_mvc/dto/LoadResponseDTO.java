package pti.sb_sigmod_mvc.dto;

import java.util.List;

public class LoadResponseDTO {
    List<AuthorDTO> authors;
    String errorMessage;

    public LoadResponseDTO(List<AuthorDTO> authors, String errorMessage) {
        this.authors = authors;
        this.errorMessage = errorMessage;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
