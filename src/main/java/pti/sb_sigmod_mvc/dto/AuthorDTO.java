package pti.sb_sigmod_mvc.dto;

public class AuthorDTO {
	
	private String name;
	private Integer counter;
	private String searchKeyWord;
	private String saveType;
	
	
	public AuthorDTO(String name, Integer counter) {
		super();
		this.name = name;
		this.counter = counter;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
}
