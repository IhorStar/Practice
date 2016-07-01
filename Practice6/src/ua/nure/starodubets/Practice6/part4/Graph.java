package ua.nure.starodubets.Practice6.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The class is an undirected graph.
 *
 * @author Ihor Starodubets
 *
 */
public class Graph {

	/**
	 * Number of the vertex
	 */
	private int vertexNumber;

	/**
	 * The map of graphs
	 */
	private Map<String, List<String>> graphMap = new TreeMap<String, List<String>>();

	/**
	 * The class constructor
	 *
	 * @param vertexNumber number of the vertex
	 */
	Graph(int vertexNumber) {
		setVertexNumber(vertexNumber);
		addVertexes();
	}

	/**
	 * The method gets number of the vertex
	 *
	 * @return number of the vertex
	 */
	public int getVertexNumber() {
		return vertexNumber;
	}

	/**
	 * The method sets number of the vertex
	 *
	 * @param vertexNumber number of the vertex
	 */
	public void setVertexNumber(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	/**
	 * The method gets map of graphs
	 *
	 * @return map of graphs
	 */
	public Map<String, List<String>> getGraphMap() {
		return graphMap;
	}

	/**
	 * The method adds vertexes to the map of graphs
	 */
	public void addVertexes() {
		for (int i = 1; i <= getVertexNumber(); i++) {
			graphMap.put(String.valueOf(i), new ArrayList<String>());
		}
	}

	/**
	 * The method adds an edge
	 *
	 * @param firstVertex a first vertex
	 * @param secondVertex a second vertex
	 * @return String with result of adding the edge
	 */
	public String addEdge(String firstVertex, String secondVertex) {

		if (Integer.valueOf(firstVertex) > getVertexNumber()
				|| Integer.valueOf(secondVertex) > getVertexNumber()
				|| Integer.valueOf(firstVertex) < 0
				|| Integer.valueOf(secondVertex) < 0) {
			return firstVertex + " and(or) " + secondVertex
					+ " are illegal vertexes, select vertexes from 1 to "
					+ getVertexNumber();
		}
		if (Integer.valueOf(firstVertex).equals(Integer.valueOf(secondVertex))) {
			return "Cannot create edge, vertexes are equal";
		}
		List<String> list = getGraphMap().get(firstVertex);
		List<String> list2 = getGraphMap().get(secondVertex);
		if (list.contains(secondVertex) && list2.contains(firstVertex)) {
			return "The edge between " + firstVertex + " and  " + secondVertex
					+ " already exists";
		}
		list.add(secondVertex);
		list2.add(firstVertex);
		return "added a new edge between " + firstVertex + " and "
				+ secondVertex;
	}

	/**
	 * The method removes an edge
	 *
	 * @param firstVertex a first vertex
	 * @param secondVertex a second vertex
	 * @return String with result of removing the edge
	 */
	public String removeEdge(String firstVertex, String secondVertex) {
		List<String> list = getGraphMap().get(firstVertex);
		List<String> list2 = getGraphMap().get(secondVertex);
		if (list.contains(secondVertex) && list2.contains(firstVertex)) {
			list.remove(secondVertex);
			list2.remove(firstVertex);
			return "Edge between " + firstVertex + " and  " + secondVertex
					+ " was removed";
		} else {
			return "The edge between " + firstVertex + " and " + secondVertex
					+ " does not exist";
		}
	}
}
