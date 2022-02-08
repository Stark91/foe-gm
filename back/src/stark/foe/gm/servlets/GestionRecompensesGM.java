package stark.foe.gm.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stark.foe.gm.beans.Age;
import stark.foe.gm.beans.GM;
import stark.foe.gm.beans.RecompensesGM;
import stark.foe.gm.dao.DAORecompensesGM;
import stark.foe.gm.dao.DAOAge;
import stark.foe.gm.dao.DAOFactory;
import stark.foe.gm.dao.DAOGM;

@SuppressWarnings("serial")
public class GestionRecompensesGM extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PARAM_AGE = "age";
	public static final String PARAM_BONUS_ARCHE = "bonusArche";
	public static final String PARAM_GM = "gm";
	public static final String PARAM_ID_AGE = "idAge";
	public static final String PARAM_ID_GM = "idGM";
	public static final String PARAM_MAPRECOMPENSESGM = "mapRecompensesGM";
	public static final String PARAM_NIVEAU = "niveau";
	public static final String PARAM_NIVEAU_DEPART = PARAM_NIVEAU + "Depart";
	public static final String PARAM_NIVEAU_FIN = PARAM_NIVEAU + "Fin";
	public static final String PARAM_NIVEAU_MAX = "niveauMax";
	public static final String PARAM_RECOMPENSESGM = "recompensesGM";
	public static final String PARAM_RECOMPENSESGM_BONUS = "recompensesGMBonus";
	public static final String PARAM_SECU = "secu";
	public static final String PARAM_SECU_TOTAL_RUSH = "secuTotalRush";
	public static final String PARAM_SVG_GM = "svgGM";
	public static final String PARAM_TAB = "paramTab";
	public static final String PARAM_TOTAL_RUSH = "totalRush";
	public static final String VUE = "/WEB-INF/afficherRecompensesGM.jsp";
	
	private DAORecompensesGM daoRecompensesGM;
	private DAOGM daoGM;
	private DAOAge daoAge;
	
	public void init() throws ServletException {
		this.daoRecompensesGM = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDAORecompensesGM();
		this.daoGM = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDAOGM();
		this.daoAge = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDAOAge();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int niveau = 1;
		int niveauDepart = 0;
		int niveauFin = 10;
		request.setAttribute(PARAM_NIVEAU_DEPART, niveauDepart);
		request.setAttribute(PARAM_NIVEAU_FIN, niveauFin);
		request.setAttribute(PARAM_TAB, "0");
		
		ArrayList<Float> arrayBonusArche = getArrayBonusArche(request, false);
		
		initVue(request, response, arrayBonusArche, niveau, niveauDepart, niveauFin);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		int niveau;
		try {
			niveau = Integer.parseInt((request.getParameter(PARAM_NIVEAU)));
		} catch (Exception e) {
			niveau = 1;
		}
		
		int niveauDepart = 0;
		try {
			niveauDepart = Integer.parseInt((request.getParameter(PARAM_NIVEAU_DEPART)));
		} catch (Exception e) {
			niveauDepart = 0;
		}
		
		int niveauFin = 10;
		try {
			niveauFin = Integer.parseInt((request.getParameter(PARAM_NIVEAU_FIN)));
		} catch (Exception e) {
			niveauFin = 10;
		}

		request.setAttribute(PARAM_NIVEAU_DEPART, niveauDepart);
		request.setAttribute(PARAM_NIVEAU_FIN, niveauFin);
		
		ArrayList<Float> arrayBonusArche = getArrayBonusArche(request, false);
		
		initVue(request, response, arrayBonusArche, niveau, niveauDepart, niveauFin);
	}
	
	private String getSVGFromGM(GM gm) {
		String svgCode = "";
		Short x = gm.getX();
		Short y = gm.getY();
		Short baseWidth = 40;
		Short baseHeight = 18;
		Short width = (short) (baseWidth*(x+y+2));
		Short height = (short) (baseHeight*(x+y+2));
		
		svgCode += "<svg height=\"" + height +"\" width=\"" + width + "\">";
		
		for (int i = 0; i <= x; i++) {
			svgCode += "<line class=\"svg-gm\" x1=\"" + ((y+1)*baseWidth + (i*baseWidth)) + "\" y1=\"" + (height - (i*baseHeight)) + "\" x2=\"" + ((y+1)*baseWidth - ((y - i)*baseWidth)) + "\" y2=\"" + (height - ((y + i)*baseHeight)) + "\" />";
		}

		for (int i = 0; i <= y; i++) {
			svgCode += "<line class=\"svg-gm\" x1=\"" + ((y+1)*baseWidth - (i*baseWidth)) + "\" y1=\"" + (height - (i*baseHeight)) + "\" x2=\"" + ((y+1)*baseWidth + ((x - i)*baseWidth)) + "\" y2=\"" + (height - ((x + i)*baseHeight)) + "\" />";
		}
		
		svgCode += "</svg>";
		
		return svgCode;
	}
	
	private RecompensesGM getRecompenseGMBonus(RecompensesGM recompensesGM, ArrayList<Float> arrayBonusArche) {
		RecompensesGM recompensesGMBonus = new RecompensesGM(recompensesGM.getAge());
		recompensesGMBonus.setNiveau(recompensesGM.getNiveau());
		recompensesGMBonus.setTotal(recompensesGM.getTotal());
		recompensesGMBonus.setP1((int) (Math.round(recompensesGM.getP1() * (1 + arrayBonusArche.get(0)/100))));
		recompensesGMBonus.setP2((int) (Math.round(recompensesGM.getP2() * (1 + arrayBonusArche.get(1)/100))));
		recompensesGMBonus.setP3((int) (Math.round(recompensesGM.getP3() * (1 + arrayBonusArche.get(2)/100))));
		recompensesGMBonus.setP4((int) (Math.round(recompensesGM.getP4() * (1 + arrayBonusArche.get(3)/100))));
		recompensesGMBonus.setP5((int) (Math.round(recompensesGM.getP5() * (1 + arrayBonusArche.get(4)/100))));
		
		if(recompensesGMBonus.getTotal()-2*recompensesGMBonus.getP1() < 0) {
			recompensesGMBonus.setSecuP1(0);
		} else {
			recompensesGMBonus.setSecuP1(recompensesGMBonus.getTotal()-2*recompensesGMBonus.getP1());
		}
		
		if(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-2*recompensesGMBonus.getP2() < 0) {
			recompensesGMBonus.setSecuP2(0);
		} else {
			recompensesGMBonus.setSecuP2(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-2*recompensesGMBonus.getP2());
		}
		
		if(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-2*recompensesGMBonus.getP3() < 0) {
			recompensesGMBonus.setSecuP3(0);
		} else {
			recompensesGMBonus.setSecuP3(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-2*recompensesGMBonus.getP3());
		}
		
		if(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-recompensesGMBonus.getP3()-recompensesGMBonus.getSecuP3()-2*recompensesGMBonus.getP4() < 0) {
			recompensesGMBonus.setSecuP4(0);
		} else {
			recompensesGMBonus.setSecuP4(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-recompensesGMBonus.getP3()-recompensesGMBonus.getSecuP3()-2*recompensesGMBonus.getP4());
		}
		
		if(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-recompensesGMBonus.getP3()-recompensesGMBonus.getSecuP3()-recompensesGMBonus.getP4()-recompensesGMBonus.getSecuP4()-2*recompensesGMBonus.getP5() < 0) {
			recompensesGMBonus.setSecuP5(0);
		} else {
			recompensesGMBonus.setSecuP5(recompensesGMBonus.getTotal()-recompensesGMBonus.getP1()-recompensesGMBonus.getSecuP1()-recompensesGMBonus.getP2()-recompensesGMBonus.getSecuP2()-recompensesGMBonus.getP3()-recompensesGMBonus.getSecuP3()-recompensesGMBonus.getP4()-recompensesGMBonus.getSecuP4()-2*recompensesGMBonus.getP5());
		}
		
		return recompensesGMBonus;
	}
	
	private ArrayList<Float> getArrayBonusArche(HttpServletRequest request, boolean rush) {
		ArrayList<Float> arrayListBonusArcheRush = new ArrayList<Float>();
		
		try {
			arrayListBonusArcheRush.add(Float.parseFloat(request.getParameter(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P1")));
		} catch (Exception e) {
			arrayListBonusArcheRush.add(90.0f);
		}
		
		try {
			arrayListBonusArcheRush.add(Float.parseFloat(request.getParameter(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P2")));
		} catch (Exception e) {
			arrayListBonusArcheRush.add(90.0f);
		}
		
		try {
			arrayListBonusArcheRush.add(Float.parseFloat(request.getParameter(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P3")));
		} catch (Exception e) {
			arrayListBonusArcheRush.add(90.0f);
		}
		
		try {
			arrayListBonusArcheRush.add(Float.parseFloat(request.getParameter(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P4")));
		} catch (Exception e) {
			arrayListBonusArcheRush.add(90.0f);
		}
		
		try {
			arrayListBonusArcheRush.add(Float.parseFloat(request.getParameter(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P5")));
		} catch (Exception e) {
			arrayListBonusArcheRush.add(90.0f);
		}
		
		request.setAttribute(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P1", arrayListBonusArcheRush.get(0));
		request.setAttribute(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P2", arrayListBonusArcheRush.get(1));
		request.setAttribute(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P3", arrayListBonusArcheRush.get(2));
		request.setAttribute(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P4", arrayListBonusArcheRush.get(3));
		request.setAttribute(PARAM_BONUS_ARCHE + (rush ? "Rush" : "")+ "P5", arrayListBonusArcheRush.get(4));
		
		return arrayListBonusArcheRush;
	}
	
	private void initVue(HttpServletRequest request, HttpServletResponse response, ArrayList<Float> arrayBonusArche, int niveau, int niveauDepart, int niveauFin) throws ServletException, IOException  {
		Age ageGM = new Age();
		try{
			ageGM.setId(Long.parseLong(request.getParameter(PARAM_ID_AGE)));
		} catch (Exception e) {
			ageGM.setId(Long.parseLong("0"));
		}
		ageGM = daoAge.readOnlyAge(ageGM.getId());
		
		GM gm = new GM();
		gm.setAge(ageGM);
		request.setAttribute(PARAM_AGE, ageGM);
		try {
			gm.setId(Long.parseLong(request.getParameter(PARAM_ID_GM)));
		} catch (Exception e) {
			gm.setId(Long.parseLong("0"));
		}
		gm = daoGM.readOnlyGM(gm.getId());
		request.setAttribute(PARAM_GM, gm);
		
		RecompensesGM recompensesGM = new RecompensesGM(ageGM);
		recompensesGM.setNiveau(niveau);
		recompensesGM = daoRecompensesGM.readRecompensesGM(ageGM.getId(), niveau);
		request.setAttribute(PARAM_RECOMPENSESGM, recompensesGM);
		
		RecompensesGM recompensesGMBonus = getRecompenseGMBonus(recompensesGM, arrayBonusArche);
		request.setAttribute(PARAM_RECOMPENSESGM_BONUS, recompensesGMBonus);
		
		String svg = getSVGFromGM(gm);
		request.setAttribute(PARAM_SVG_GM, svg);
		
		ArrayList<Float> arrayListBonusArcheRush = getArrayBonusArche(request, true);
		
		int secuTotalRush = 0;
		int totalRush = 0;
		List<RecompensesGM> listRecompensesGM =  daoRecompensesGM.readRushGM(gm.getAge().getId(), niveauDepart, niveauFin);
		TreeMap<Integer,RecompensesGM> mapRecompensesGM = new TreeMap<Integer, RecompensesGM>();
		for(RecompensesGM recompensesGMRush : listRecompensesGM) {
			recompensesGMRush.setP1((int) (Math.round(recompensesGMRush.getP1() * (1 + arrayListBonusArcheRush.get(0)/100))));
			recompensesGMRush.setP2((int) (Math.round(recompensesGMRush.getP2() * (1 + arrayListBonusArcheRush.get(1)/100))));
			recompensesGMRush.setP3((int) (Math.round(recompensesGMRush.getP3() * (1 + arrayListBonusArcheRush.get(2)/100))));
			recompensesGMRush.setP4((int) (Math.round(recompensesGMRush.getP4() * (1 + arrayListBonusArcheRush.get(3)/100))));
			recompensesGMRush.setP5((int) (Math.round(recompensesGMRush.getP5() * (1 + arrayListBonusArcheRush.get(4)/100))));
			
			if(recompensesGMRush.getTotal()-2*recompensesGMRush.getP1() < 0) {
				recompensesGMRush.setSecuP1(0);
			} else {
				recompensesGMRush.setSecuP1(recompensesGMRush.getTotal()-2*recompensesGMRush.getP1());
			}
			
			if(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-2*recompensesGMRush.getP2() < 0) {
				recompensesGMRush.setSecuP2(0);
			} else {
				recompensesGMRush.setSecuP2(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-2*recompensesGMRush.getP2());
			}
			
			if(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-2*recompensesGMRush.getP3() < 0) {
				recompensesGMRush.setSecuP3(0);
			} else {
				recompensesGMRush.setSecuP3(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-2*recompensesGMRush.getP3());
			}
			
			if(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-recompensesGMRush.getP3()-recompensesGMRush.getSecuP3()-2*recompensesGMRush.getP4() < 0) {
				recompensesGMRush.setSecuP4(0);
			} else {
				recompensesGMRush.setSecuP4(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-recompensesGMRush.getP3()-recompensesGMRush.getSecuP3()-2*recompensesGMRush.getP4());
			}
			
			if(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-recompensesGMRush.getP3()-recompensesGMRush.getSecuP3()-recompensesGMRush.getP4()-recompensesGMRush.getSecuP4()-2*recompensesGMRush.getP5() < 0) {
				recompensesGMRush.setSecuP5(0);
			} else {
				recompensesGMRush.setSecuP5(recompensesGMRush.getTotal()-recompensesGMRush.getP1()-recompensesGMRush.getSecuP1()-recompensesGMRush.getP2()-recompensesGMRush.getSecuP2()-recompensesGMRush.getP3()-recompensesGMRush.getSecuP3()-recompensesGMRush.getP4()-recompensesGMRush.getSecuP4()-2*recompensesGMRush.getP5());
			}
			
			secuTotalRush += recompensesGMRush.getSecuP1() + recompensesGMRush.getSecuP2() + recompensesGMRush.getSecuP3() + recompensesGMRush.getSecuP4() + recompensesGMRush.getSecuP5() + recompensesGMRush.getP5();
			
			totalRush += recompensesGMRush.getTotal();
			
			mapRecompensesGM.put(recompensesGMRush.getId().intValue(), recompensesGMRush);
		}
		
		request.setAttribute(PARAM_NIVEAU_MAX, daoRecompensesGM.readNiveauMax(ageGM.getId()));
		
		request.setAttribute(PARAM_SECU_TOTAL_RUSH, secuTotalRush);
		request.setAttribute(PARAM_TOTAL_RUSH, totalRush);
		request.setAttribute(PARAM_MAPRECOMPENSESGM, mapRecompensesGM);
		request.setAttribute(PARAM_TAB, request.getParameter(PARAM_TAB));
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
