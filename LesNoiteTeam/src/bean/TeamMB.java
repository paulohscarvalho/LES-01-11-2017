package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import dao.TeamDAO;
import dao.TeamDAOImpl;
import entidade.Team;

@ManagedBean
@SessionScoped
public class TeamMB implements Serializable {
	private static final long serialVersionUID = -3009074750360499144L;
	private List<Team> times = new ArrayList<Team>();
	private TeamDAO tDAO = new TeamDAOImpl();

	private Team timeAtual = new Team();

	public Team getTimeAtual() {
		return timeAtual;
	}
	public void setTimeAtual(Team timeAtual) {
		this.timeAtual = timeAtual;
	}
	
	public List<Team> getTimes() {
		return times;
	}
	public void setTimes(List<Team> times) {
		this.times = times;
	}
	
	public String adicionar() {
		tDAO.adicionar( timeAtual );
		timeAtual = new Team();
		FacesMessage msg = new FacesMessage("Time adicionado");
		FacesContext.getCurrentInstance().addMessage( null, msg);
		return "";
	}
	
	public void validaData(FacesContext fc, UIComponent ui, Object o) { 
		Date d = (Date) o;
		Calendar cal = Calendar.getInstance();
		cal.setTime( d );
		if (cal.get(Calendar.YEAR) >= 2012) { 
			FacesMessage msg = new FacesMessage("O time deve ter ao menos 5 anos");
			throw new ValidatorException( msg );
		}
	}
	
	public String pesquisar() { 
		setTimes(tDAO.pesquisar( timeAtual.getNome() ));
		FacesMessage msg = new FacesMessage("Pesquisa efetuada, foram encontrados " + getTimes().size() + " times");
		FacesContext.getCurrentInstance().addMessage( null, msg);
		if (getTimes().size() > 0) { 
			timeAtual = getTimes().get(0);
		}
		return "";
	}
}
