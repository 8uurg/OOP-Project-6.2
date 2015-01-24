package voetbalmanager.controller.teammanager;

import java.util.ArrayList;
import java.util.Random;

import voetbalmanager.exceptions.OpstellingException;
import voetbalmanager.exceptions.TransferException;
import voetbalmanager.model.BeschikbareSpeler;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Opstelling;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;

/**
 * Algemene klasse voor teambeheer
 */
public class AITeamManager {
	private Random rng= new Random();
	private Competitie competitie;
	private Team spelerteam;
	
	/**
	 * De totale managementCycle na een speelweek.
	 * @param competitie competitie die managed moet worden.
	 */
	public void runManagementCycle(Competitie competitie){
		this.competitie = competitie;
		ArrayList<Team> teams = competitie.getTeams();
		for(int i=0;i<teams.size();i++){
			if(teams.get(i).isComputerGestuurd())
				this.manageTeam(teams.get(i), competitie);
			if(teams.get(i).isSpelerBestuurd())
				this.spelerteam=teams.get(i);			
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
		if(context.getTransferMarkt().getOudTeam(spelerteam)||context.getTransferMarkt().getVerhandelbareSpelers().size()<10){
			if((spelers.size()<Team.maxAantalSpelers()||context.getTransferMarkt().getVerhandelbareSpelers().size()>10)&&rng.nextDouble()>0.45){
				if(!checkSpelerMarkt(spelers).isEmpty())
					spelerKopen(checkSpelerMarkt(spelers),spelers);
			}
			else{
		/*		if(checkSpelerMarkt(spelers).isEmpty()){
					if(moetVerkopen(team))
						spelerVerkopen(spelers);
				//	spelerKopen(checkSpelerMarkt(spelers),spelers);*/
			//	}else{
					if(moetVerkopen(team)&&rng.nextDouble()>0.15)
						spelerVerkopen(spelers);
			//	}
			}
		}
		else if(!(spelers.size()>Team.maxAantalSpelers())){
			ArrayList<Speler> mogelijkeTransfer = zoekNaarTransfer(team);
			if(!mogelijkeTransfer.isEmpty()){
				int a = rng.nextInt(mogelijkeTransfer.size());
				if(team.doeBod(mogelijkeTransfer.get(a))<team.getBudget()/4){
					biedTransferAan(team,mogelijkeTransfer.get(a),team.doeBod(mogelijkeTransfer.get(a)));
				}
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
		int b = rng.nextInt(100);
		Team a = sp.getTeam();
		if(!(b>5)){
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
			int a = rng.nextInt(100);
			if(moetVerkopen(speler.getTeam())){
				if(speler.overweegVerkopen(spelers)){
					if(a<30){
						competitie.getTransferMarkt().maakVerhandelbaar(speler);
						System.out.println("Er is een speler verkocht");
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
				System.out.println("Team "+spelers.get(1).getTeam().getNaam()+" Heeft de speler "+speler.getSpeler().getNaam()+ " Gekocht.");
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
		if(competitie.Sorteren("Punten").getTeams().indexOf(team)>competitie.getTeams().size()/2/*&&rng.nextFloat()<0.5*/){
			return true;
		}
		else if(competitie.Sorteren("Punten").getTeams().indexOf(team)<competitie.getTeams().size()/2/*&&rng.nextFloat()<0.1*/){
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
		competitie.Sorteren("Punten");
		for(Team tf: competitie.getTeams()){
			if(competitie.getTeams().indexOf(team)>competitie.getTeams().indexOf(tf)){
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
	
	
	public void genereerOpstelling(Team team) {
		Opstelling opstelling = team.getOpstelling();
		opstelling.clear();
		
		ArrayList<Speler> a = new ArrayList<Speler>();
		ArrayList<Speler> m = new ArrayList<Speler>();
		ArrayList<Speler> v = new ArrayList<Speler>();
		ArrayList<Speler> d = new ArrayList<Speler>();
		
		// TODO: overleg alle spelers op alle posities, of gebruik maken van type?
		// In geval van alle spelers op alle posities, let op dat je een speler niet op meer dan 1 positie zet.
		
		for(Speler s:team.getSelectie()) {
			switch(s.getType()) {
			case Aanvaller:
				a.add(s);
				break;
			case Middenvelder:
				m.add(s);
				break;
			case Verdediger:
				v.add(s);
				break;
			case Doelman:
				d.add(s);
				break;
			}
		}
		
		SpelerStatVergelijker ac = new SpelerStatVergelijker(1, 0);
		a.sort(ac);
		SpelerStatVergelijker mc = new SpelerStatVergelijker(1, 1);
		m.sort(mc);
		SpelerStatVergelijker vc = new SpelerStatVergelijker(0.5F, 1);
		v.sort(vc);
		SpelerStatVergelijker dc = new SpelerStatVergelijker(0, 1);
		d.sort(dc);
		
		try {
		for(int i=0; i<opstelling.getAanvallers(); ++i) {
			opstelling.voegToeAanvaller(a.get(i));
		}
		for(int i=0; i<opstelling.getMiddenvelders(); ++i) {
			opstelling.voegToeMiddenvelder(m.get(i));
		}
		for(int i=0; i<opstelling.getVerdedigers(); ++i) {
			opstelling.voegToeVerdediger(v.get(i));
		}
		for(int i=0; i<opstelling.getDoelmannen(); ++i) {
			opstelling.voegToeDoelman(d.get(i));
		}
		} catch(OpstellingException e) {
		}
		
	}
	
}
