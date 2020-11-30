package model.entities;

import java.io.Serializable;

public class AtuationZone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String zoneName;
	private String zonaNorte;
	private String zonaSul;
	private String zonaLeste;
	private String zonaOeste;
	
	public AtuationZone() {}

	public AtuationZone(Integer id, String zoneName, String zonaNorte, String zonaSul, String zonaLeste, String zonaOeste) {
		this.id = id;
		this.zoneName = zoneName;
		this.zonaNorte = zonaNorte;
		this.zonaSul = zonaSul;
		this.zonaLeste = zonaLeste;
		this.zonaOeste = zonaOeste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZonaNorte() {
		return zonaNorte;
	}

	public void setZonaNorte(String zonaNorte) {
		this.zonaNorte = zonaNorte;
	}

	public String getZonaSul() {
		return zonaSul;
	}

	public void setZonaSul(String zonaSul) {
		this.zonaSul = zonaSul;
	}

	public String getZonaLeste() {
		return zonaLeste;
	}

	public void setZonaLeste(String zonaLeste) {
		this.zonaLeste = zonaLeste;
	}

	public String getZonaOeste() {
		return zonaOeste;
	}

	public void setZonaOeste(String zonaOeste) {
		this.zonaOeste = zonaOeste;
	}
	
	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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
		AtuationZone other = (AtuationZone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
