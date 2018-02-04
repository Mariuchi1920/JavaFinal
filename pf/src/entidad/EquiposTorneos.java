package entidad;

import java.util.ArrayList;



public class EquiposTorneos {
	
   private Torneo torneo;
   private ArrayList<Equipo> equipos;
   
   
	public Torneo getTorneo() {
		return torneo;
	}
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	   

}
