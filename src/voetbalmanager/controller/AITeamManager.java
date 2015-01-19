package voetbalmanager.controller;

import java.util.ArrayList;
import java.util.Random;

import voetbalmanager.exceptions.TransferException;
import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;
import voetbalmanager.model.TransferMarkt;

/**
 * Algemene klasse voor teambeheer
 */
public class AITeamManager {
	private static Random rng;
	private Competitie competitie;
	private Team spelerteam;
	
	/**
	 * De totale managementCycle na een speelweek.
	 * @param competitie competitie die managed moet worden.
	 */
	public void runManagementCycle(Competitie competitie){
		this.competitie = competitie;
		ArrayList<Team> teams = competitie.getTeams();
		for(Team team:teams){
			if(team.isComputerGestuurd())
				this.manageTeam(team, competitie);
			if(team.isSpelerBestuurd())
				this.spelerteam=team;			
		}
	}
	
	/**
	 * Managet een team door te kijken naar de teamgrootte en het budget van het team.
	 * @param team Het eerste team dat gemanaged moet worden.
	 * @param context De competitie waarin het team zich bevindt
	 */
	private void manageTeam(Team team, Competitie context){
		// TODO Schrijf code voor verwerken team.
		// Afhankelijk van gekozen systeem: vereist speelschema.
		ArrayList<Speler> spelers = team.getSelectie();
		if(context.getTransferMarkt().getOudTeam(spelerteam)){
			if(!(spelers.size()==Team.maxAantalSpelers())){
				if(!checkSpelerMarkt(spelers).isEmpty())
					spelerKopen(checkSpelerMarkt(spelers),spelers);
			}
			else{
				if(!checkSpelerMarkt(spelers).isEmpty()&&rng.nextDouble()<0.1){
					moetVerkopen(team);
					spelerVerkopen(spelers);
					spelerKopen(checkSpelerMarkt(spelers),spelers);
				}
			}
		}
		if(!(spelers.size()==Team.maxAantalSpelers())){
			ArrayList<Speler> mogelijkeTransfer = zoekNaarTransfer(team);
			int a = rng.nextInt(mogelijkeTransfer.size());
			if(team.doeBod(mogelijkeTransfer.get(a))>team.getBudget()/3){
				biedTransferAan(team,mogelijkeTransfer.get(a),team.doeBod(mogelijkeTransfer.get(a)));
			}
		}
	}
	
	/**
	 * Biedt een transfer aan aan een team voor een specifieke speler
	 * @param team Het team wat een transfer aan wil bieden.
	 * @param sp De speler die het team graag wil overkopen.
	 * @param prijs De prijs die er voor de speler betaald wordt.
	 */
	private void biedTransferAan(Team team,Speler sp,int prijs) {
		Team a = sp.getTeam();
		if(!(rng.nextInt(100)>1)){
			try {
				competitie.getTransferMarkt().Transfer(a, team, sp,prijs);
			} catch (TransferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Checkt of het team spelers heeft die hij wil verkopen
	 * @param spelers De spelers die te verkopen zijn.
	 */
	private void spelerVerkopen(ArrayList<Speler> spelers){		
		for(Speler speler:spelers){
			if(moetVerkopen(speler.getTeam())){
				if(speler.getSpelerWaarde()<competitie.getTransferMarkt().getMinWaarde()){
					if(rng.nextDouble()<0.5){
						competitie.getTransferMarkt().maakVerhandelbaar(speler);
						break;
					}
				}
			}
		}
		
	}
	
	/**
	 * Checkt alle potentiele aankopen en kiest een speler om te kopen. (Deze hoeft niet gekocht te worden)
	 * @param potentieel De potentiele aankopen.
	 * @param spelers De spelers van het team waarmee de potentiele aankopen vergeleken worden.
	 */
	private void spelerKopen(ArrayList<BeschikbareSpeler> potentieel, ArrayList<Speler> spelers){
		int budget=spelers.get(0).getTeam().getBudget();
		for(BeschikbareSpeler speler: potentieel){
			int a = speler.besluitKoop(spelers);
			int b = competitie.Sorteren("Punten").getTeams().indexOf(speler.getOudTeam());
			if(rng.nextDouble()<(0.1+(0.1*a)+(0.1-0.1/competitie.getTeams().size()*b))&&budget/2>speler.getSpeler().getPrijs()){
				competitie.getTransferMarkt().koopSpeler(spelers.get(0).getTeam(),speler);
				break;
			}
		}
	}
	
	/**
	 * Checkt of er potentiele aankopen zijn die het team verbeteren.
	 * @param spelers Spelers uit het team die vergeleken moeten worden met potentiele spelers
	 * @return alle potentiele spelers die gekocht zouden kunnen worden door het team.
	 */
	public ArrayList<BeschikbareSpeler> checkSpelerMarkt(ArrayList<Speler> spelers){
		ArrayList<BeschikbareSpeler> potentieel = new ArrayList<BeschikbareSpeler>();
		potentieel = competitie.getTransferMarkt().interesse(spelers);
		return potentieel;
	}
	
	/**
	 * Geeft aan of het slim is voor een team om een speler te verkopen.
	 * @param team Het team dat gecheckt wordt.
	 * @return geeft aan of er een speler verkocht moet worden.
	 */
	private boolean moetVerkopen(Team team){
		if(competitie.Sorteren("Punten").getTeams().indexOf(team)>competitie.getTeams().size()/2&&rng.nextFloat()<0.5){
			return true;
		}
		else if(competitie.Sorteren("Punten").getTeams().indexOf(team)<competitie.getTeams().size()/2&&rng.nextFloat()<0.1){
			return true;
		}
		return false;
	}
	
	/**
	 * Check voor eventuele transfers.
	 * @param team Het team dat zoekt naar een transfer
	 * @return geeft Spelers terug die je kunt transferren.
	 */
	private ArrayList<Speler> zoekNaarTransfer(Team team){
		ArrayList<Speler> speler = new ArrayList<Speler>();
		for(Team tf: competitie.getTeams()){
			if(competitie.Sorteren("Punten").getTeams().indexOf(team)>competitie.Sorteren("Punten").getTeams().indexOf(tf)){
				for(Speler sp: tf.getSelectie()){
					if(sp.transferAanbieden(team.getSelectie())){
						speler.add(sp);
						break;
					}
				}
			}
		}
		return speler;
	}
	
}
