package model.entities;

import java.io.Serializable;

public class Corretor implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String folga;
	
	AtuationZone atuationZone;
	
	public Corretor() {}
	
	public Corretor(Integer id, String name, String folga) {
		this.id = id;
		this.name = name;
		this.folga = folga;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolga() {
		return folga;
	}

	public void setFolga(String folga) {
		this.folga = folga;
	}
	
	public AtuationZone getAtuationZone() {
		return atuationZone;
	}	
	
	public AtuationZone setAtuationZone(AtuationZone atuationZone) {
		return this.atuationZone = atuationZone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corretor other = (Corretor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
