package com.cooksys.ftd.assignments.day.three.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cooksys.ftd.assignments.day.three.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.day.three.collections.model.Capitalist;
import com.cooksys.ftd.assignments.day.three.collections.model.FatCat;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	Map<FatCat, Set<Capitalist>> megaCorp = new HashMap<>();

	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it
	 * and return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add
	 * it and return false
	 *
	 * @param capitalist
	 *            the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {
		if (capitalist == null)
			return false;

		// If element is already in hierarchy
		if (has(capitalist))
			return false;

		// If element has no parent and is not a parent itself, return false
		if (!capitalist.hasParent() && !(capitalist instanceof FatCat))
			return false;

		// If element has no parent and is a parent, add to heirarchy.
		if (!capitalist.hasParent() && (capitalist instanceof FatCat)) {
			megaCorp.put((FatCat) capitalist, new HashSet<>());
			return true;
		}

		// If element has parent and parent isn't in hierarchy, add parent to
		// hierarchy then add capitalist.
		if (capitalist.hasParent() && !megaCorp.containsKey(capitalist.getParent())) {
			add(capitalist.getParent());
			if (capitalist instanceof FatCat)
				megaCorp.put((FatCat) capitalist, new HashSet<>());
			megaCorp.get((FatCat)capitalist.getParent()).add(capitalist);
			return true;
		} else {
			megaCorp.get((FatCat)capitalist.getParent()).add(capitalist);
			return true;
		}
	}

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false
	 *         otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
		if (megaCorp.containsKey(capitalist))
			return true;

		// If element is in hierarchy, return false.
		for (Set<Capitalist> set : megaCorp.values()) {
			if (set.contains(capitalist))
				return true;
		}

		return false;

	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		Set<Capitalist> elements = new HashSet<>(getParents());
		
		for (Set<Capitalist> set : megaCorp.values())
			elements.add((Capitalist) set);
		
		return (Set<Capitalist>) elements;
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no
	 *         parents have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		return (Set<FatCat>) new HashSet<>(megaCorp.keySet());
	}

	/**
	 * @param fatCat
	 *            the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a
	 *         direct parent, or an empty set if the parent is not present in
	 *         the hierarchy or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		if (fatCat == null)
			return (Set<Capitalist>) new HashSet<Capitalist>();
		return (Set<Capitalist>) new HashSet<>(megaCorp.get(fatCat));
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of
	 *         the associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		return (Map<FatCat, Set<Capitalist>>) new HashMap<>(megaCorp);
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the
	 *         given element has no parent or if its parent is not in the
	 *         hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {
		List<FatCat> linkedList = new LinkedList<>();
		if (capitalist == null || capitalist.getParent() == null)
			return (List<FatCat>)linkedList;
		
		while (capitalist.hasParent()) {
			capitalist = capitalist.getParent();
			linkedList.add((FatCat)capitalist);
		}
		
		return (List<FatCat>)linkedList;
	}

}
