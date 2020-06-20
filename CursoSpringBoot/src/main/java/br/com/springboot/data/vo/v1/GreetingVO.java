package br.com.springboot.data.vo.v1;

public class GreetingVO {

	private final long id;
	private final String content;

	public GreetingVO(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}
