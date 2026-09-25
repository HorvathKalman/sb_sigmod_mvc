package pti.sb_sigmod_mvc.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import pti.sb_sigmod_mvc.model.AuthorSaveModel;

public interface Repository extends CrudRepository<AuthorSaveModel, Integer> {


}
