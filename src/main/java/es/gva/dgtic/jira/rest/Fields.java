package es.gva.dgtic.jira.rest;

public class Fields {
	
	private String attachment;
	
	public Fields(){
		
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public String toString(){
		return "Fields{" +
                "attachment='" + attachment + '\'' +                               
                '}';
	}

}
