package es.gva.dgtic.jira.rest;

import java.util.List;

public class JiraIssue {
	private long id;
	private String key;
	private List fields;
	
	public JiraIssue(){
		
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List getFields() {
		return fields;
	}

	public void setFields(List fields) {
		this.fields = fields;
	}
	
	/*@Override
    public String toString() {
		return "JiraIssue{" +
                "id='" + id + '\'' +
                ", key=" + key +
                ", fields=" + fields +               
                '}';
    }
	*/
}
