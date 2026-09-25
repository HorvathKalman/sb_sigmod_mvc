package pti.sb_sigmod_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import pti.sb_sigmod_mvc.dto.LoadRequestDTO;
import pti.sb_sigmod_mvc.dto.LoadResponseDTO;
import pti.sb_sigmod_mvc.service.AppService;
import pti.sb_sigmod_mvc.dto.AuthorDTO;
import pti.sb_sigmod_mvc.dto.SaveResponseDTO;


@Controller
public class AppController {

    private AppService service;


    @Autowired
    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {

        return "index.html";
    }

    @GetMapping("/authors")
    public String getAuthors(
            Model model,
            AuthorDTO authorDTO
    ) {

        List<AuthorDTO> authors = service.getAuthors();

        model.addAttribute("authors", authors);
        model.addAttribute("authorDTO", authorDTO);

        return "authors.html";
    }


    @GetMapping("/authors/search")
    public String search(
            Model model,
            AuthorDTO authorDTO
    ) {

        List<AuthorDTO> searchResultDTO =
                service.getSearch(authorDTO);

        model.addAttribute("searchResultDTO", searchResultDTO);
        model.addAttribute("authorDTO", authorDTO);

        return "authors.html";
    }


    @PostMapping("/authors/save")
    public String save(
            Model model,
            AuthorDTO authorDTO
    ) {

        SaveResponseDTO saveResponseDTO =
                service.saveAuthors(authorDTO);

        model.addAttribute("saveResponseDTO", saveResponseDTO);
        model.addAttribute("authorDTO", authorDTO);

        return "authors.html";
    }

    @GetMapping("/load")
    public String load(
            Model model,
            LoadRequestDTO loadRequestDTO
    ) {

        LoadResponseDTO responseDTO = service.loadAuthors(loadRequestDTO);

        if ("OK".equals(responseDTO.getErrorMessage())) {
            model.addAttribute(
                    "authors",
                    responseDTO.getAuthors()
            );

            return "authors.html";
        }

        if ("XML_PATH_EMPTY".equals(responseDTO.getErrorMessage())) {
            model.addAttribute(
                    "errorMessage",
                    "No XML path was specified!"
            );
            return "index.html";
        }

        if ("XML_IS_EMPTY".equals(responseDTO.getErrorMessage())) {

            model.addAttribute(
                    "errorMessage",
                    "The XML file is empty!"
            );
            return "index.html";
        }

        if ("DATABASE_IS_EMPTY".equals(responseDTO.getErrorMessage())) {
            model.addAttribute(
                    "errorMessage",
                    "The database is empty!"
            );
            return "index.html";
        }

        model.addAttribute(
                "errorMessage",
                "Unknown error!"
        );
        return "index.html";
    }
}


