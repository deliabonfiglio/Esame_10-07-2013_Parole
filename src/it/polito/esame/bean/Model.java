package it.polito.esame.bean;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.esame.dao.ParoleDAO;

public class Model {
	private UndirectedGraph<Parola,DefaultEdge> graph ;
	private List<Parola> paroleCaricate;
	private Map<String, Parola> map;
	private int num;
	
	public Model(){
		this.num=0;
	}

	public List<Parola> getWords(int lunghezza) {
		if(lunghezza!= num){
			num = lunghezza;
			ParoleDAO dao = new ParoleDAO();
			paroleCaricate = dao.getAllParolaWithLength(lunghezza);
			map= new HashMap<String, Parola>();
			
			for(Parola p: paroleCaricate){
				map.put(p.getNome(), p);
			}
		}
		
		return paroleCaricate;
	}
	
	public Map<String, Parola> getMappaParole(int lunghezza){
		if(map==null){
			this.getWords(lunghezza);
		}
		
		return map;
	}
	
	public String creaGrafo(int lunghezza){
		graph = new SimpleGraph<>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph, this.getWords(lunghezza));
		
		for(Parola p1: graph.vertexSet()){
			for(Parola p2: graph.vertexSet()){
				if(p1.different(p2)){
					graph.addEdge(p1, p2);					
				}
			}
		}
		System.out.println(graph.toString());
		return "Numero di vertici: "+graph.vertexSet().size()+" numero di archi: "+graph.edgeSet().size();
	}

	public int getCollegate(Parola p){
		ConnectivityInspector<Parola,DefaultEdge> ci = new ConnectivityInspector<>(graph);
		
		return ci.connectedSetOf(p).size();
	}
	public String getMaxCollegate(){
		int max= Integer.MIN_VALUE;
		Parola pmax= null;
		
		for(Parola p: graph.vertexSet()){
			int grado = graph.degreeOf(p);
			if(grado>max){
				max= grado;
				pmax=p;
			}
		}
		
		return pmax.toString()+", con numero di collegamenti: "+max;
	}

	public Set<Parola> getPathBetween(Parola pa1, Parola pa2) {
		DijkstraShortestPath<Parola, DefaultEdge> dj = new DijkstraShortestPath<Parola, DefaultEdge>(graph, pa1, pa2);
		Set<Parola> attraversate = new LinkedHashSet<Parola>();
		
		for(DefaultEdge de : dj.getPathEdgeList()){
			Parola source = graph.getEdgeSource(de);
				
				if(!attraversate.contains(source)){
					attraversate.add(source);
				}
		}
		attraversate.add(pa2);
		//GraphPath<Parola, DefaultEdge> attraversate = dj.getPath();
		//Graphs.getPathVertexList(attraversate);
		return attraversate;
	}
	

}
