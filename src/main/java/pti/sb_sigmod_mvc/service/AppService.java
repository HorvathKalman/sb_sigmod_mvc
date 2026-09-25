package pti.sb_sigmod_mvc.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import pti.sb_sigmod_mvc.dto.LoadRequestDTO;
import pti.sb_sigmod_mvc.dto.LoadResponseDTO;
import pti.sb_sigmod_mvc.model.Author;
import pti.sb_sigmod_mvc.repository.Repository;
import org.springframework.stereotype.Service;


import pti.sb_sigmod_mvc.model.AuthorSaveModel;
import pti.sb_sigmod_mvc.xml.XmlReader;
import pti.sb_sigmod_mvc.dto.AuthorDTO;
import pti.sb_sigmod_mvc.dto.SaveResponseDTO;
import pti.sb_sigmod_mvc.xml.XmlWriter;

@Service
public class AppService {

    private XmlReader xmlReader;
    private Repository repository;
    private XmlWriter xmlWriter;


    @Autowired
    public AppService(XmlReader xmlReader, Repository repository, XmlWriter xmlWriter) {
        super();
        this.xmlReader = xmlReader;
        this.repository = repository;
        this.xmlWriter = xmlWriter;

    }


    public List<AuthorDTO> getAuthors() {

        List<AuthorDTO> authorDTOList = new ArrayList<>();

        Map<String, Integer> xmlResult = xmlReader.getAuthors();
        for (Map.Entry<String, Integer> author : xmlResult.entrySet()) {

            AuthorDTO authorDTO = new AuthorDTO(
                    author.getKey(),
                    author.getValue()
            );
            authorDTOList.add(authorDTO);
        }


        return authorDTOList;
    }


    public List<AuthorDTO> getSearch(AuthorDTO authorDTO) {
        List<AuthorDTO> resultAuthorDTOList = new ArrayList<>();

        Map<String, Integer> rawXMldatas = xmlReader.getSearch(authorDTO.getSearchKeyWord());
        for (Map.Entry<String, Integer> author : rawXMldatas.entrySet()) {

            AuthorDTO tempAuthorDTO = new AuthorDTO(
                    author.getKey(),
                    author.getValue()
            );
            resultAuthorDTOList.add(tempAuthorDTO);
        }
        return resultAuthorDTOList;
    }

    public SaveResponseDTO saveAuthors(AuthorDTO authorDTO) {

        if (authorDTO.getSaveType().equals("database")) {

            return saveAuthorsToDB(authorDTO);

        } else if (authorDTO.getSaveType().equals("xml")) {

            return saveAuthorsToXML(authorDTO);
        }

        return new SaveResponseDTO();
    }

    public SaveResponseDTO saveAuthorsToDB(AuthorDTO authorDTO) {
        SaveResponseDTO saveResponseDTO = new SaveResponseDTO();


        List<AuthorDTO> searchedAuthorsList = getSearch(authorDTO);
        List<AuthorSaveModel> authorSaveModelList = new ArrayList<>();

        for (AuthorDTO tempAuthorDTO : searchedAuthorsList) {
            AuthorSaveModel tempModel = new AuthorSaveModel(null, tempAuthorDTO.getName());
            authorSaveModelList.add(tempModel);
        }

        try {
            repository.saveAll(authorSaveModelList);
            saveResponseDTO.setStatusOK();

        } catch (Exception e) {
            e.printStackTrace();
            saveResponseDTO.setStatusFailed();
        }

        return saveResponseDTO;
    }

    public SaveResponseDTO saveAuthorsToXML(AuthorDTO authorDTO) {
        SaveResponseDTO saveResponseDTO = new SaveResponseDTO();

        List<AuthorDTO> searchedAuthorsList = getSearch(authorDTO);
        Map<String, Integer> mapForXMLSave = new HashMap<>();

        for (AuthorDTO tempAuthorDTO : searchedAuthorsList) {
            AuthorSaveModel tempModel = new AuthorSaveModel(null, tempAuthorDTO.getName());
            tempModel.setCounter(tempAuthorDTO.getCounter());
            mapForXMLSave.put(tempAuthorDTO.getName(), tempModel.getCounter());
        }
        try {
            xmlWriter.writeAuthors(mapForXMLSave);
            saveResponseDTO.setStatusOK();
        } catch (Exception e) {
            saveResponseDTO.setStatusFailed();
        }
        return saveResponseDTO;
    }

    public List<AuthorDTO> getAuthorsFromDB() {
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        List<Author> authorsModelList = new ArrayList<>();
        Iterable<AuthorSaveModel> optionalAuthor = repository.findAll();
        for (AuthorSaveModel tempAuthor : optionalAuthor) {
            AuthorDTO tempAuthorDTO = new AuthorDTO(
                    tempAuthor.getName(),
                    tempAuthor.getCounter()
            );
            authorDTOList.add(tempAuthorDTO);
        }
        return authorDTOList;
    }

    public LoadResponseDTO loadAuthors(LoadRequestDTO loadRequestDTO) {
        LoadResponseDTO responseDTO = null;
        List<AuthorDTO> authorDTOList = new ArrayList<>();

        if (loadRequestDTO.getLoadType().equals("xml")) {
            if (loadRequestDTO.getXmlPath() == null || loadRequestDTO.getXmlPath().isBlank()) {
                responseDTO = new LoadResponseDTO(
                        null,
                        "XML_PATH_EMPTY"
                );
                return responseDTO;
            }

            authorDTOList = getAuthors();

            if (authorDTOList.isEmpty()) {
                responseDTO = new LoadResponseDTO(
                        null,
                        "XML_IS_EMPTY"
                );
            } else {
                responseDTO = new LoadResponseDTO(
                        authorDTOList,
                        "OK"
                );
            }
        }

        if (loadRequestDTO.getLoadType().equals("database")) {
            authorDTOList = getAuthorsFromDB();

            if (authorDTOList.isEmpty()) {
                responseDTO = new LoadResponseDTO(
                        null,
                        "DATABASE_IS_EMPTY"
                );
            } else {
                responseDTO = new LoadResponseDTO(
                        authorDTOList,
                        "OK"
                );
            }
        }
        return responseDTO;
    }
}

















