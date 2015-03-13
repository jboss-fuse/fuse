/**
 * Copyright 2014 Red Hat, Inc.
 * 
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap.model.idoc.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.fusesource.camel.component.sap.model.idoc.IdocPackage;
import org.fusesource.camel.component.sap.model.idoc.Segment;
import org.fusesource.camel.component.sap.model.idoc.SegmentList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SegmentListImpl<S extends Segment> extends EObjectImpl implements SegmentList<S> {
	
	private Segment segmentParent;
	
	private EList<S> delegate;
	
	private EClass segmentClass;
	
	public boolean equals(Object o) {
		return delegate.equals(o);
	}

	public int hashCode() {
		return delegate.hashCode();
	}
	
	public Segment getSegmentParent() {
		return segmentParent;
	}

	public void setSegmentParent(Segment parent) {
		this.segmentParent = parent;
	}

	public EList<S> getDelegate() {
		return delegate;
	}

	public void setDelegate(EList<S> delegate) {
		this.delegate = delegate;
	}
	
	public EClass getSegmentType() {
		return segmentClass;
	}

	public void setSegmentClass(EClass segmentType) {
		this.segmentClass = segmentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SegmentListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdocPackage.Literals.SEGMENT_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public S add() {
		S newSegment = (S) segmentClass.getEPackage().getEFactoryInstance().create(segmentClass);
		
		// Initialize new segment
		((SegmentImpl)newSegment).setParent(getSegmentParent()); // Set parent segment
		((SegmentImpl)newSegment).setDocument(getSegmentParent().getDocument()); // Set Document
		
		delegate.add(newSegment);
		return newSegment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public S add(int index) {
		S newSegment = (S) segmentClass.getEPackage().getEFactoryInstance().create(segmentClass);
		
		// Initialize new segment
		((SegmentImpl)newSegment).setParent(getSegmentParent()); // Set parent segment
		((SegmentImpl)newSegment).setDocument(getSegmentParent().getDocument()); // Set Document
		
		delegate.add(index, newSegment);
		return newSegment;
	}

	@Override
	public boolean add(S e) {
		return delegate.add(e);
	}

	@Override
	public void add(int index, S element) {
		delegate.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends S> c) {
		return delegate.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends S> c) {
		return delegate.addAll(index,c);
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}	

	@Override
	public S get(int index) {
		return delegate.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public Iterator<S> iterator() {
		return delegate.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	@Override
	public ListIterator<S> listIterator() {
		return delegate.listIterator();
	}

	@Override
	public ListIterator<S> listIterator(int index) {
		return delegate.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return delegate.remove(o);
	}

	@Override
	public S remove(int index) {
		return delegate.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	public void move(int newPosition, S object) {
		delegate.move(newPosition, object);
	}

	public S move(int newPosition, int oldPosition) {
		return delegate.move(newPosition, oldPosition);
	}

	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	public S set(int index, S element) {
		return delegate.set(index, element);
	}

	public int size() {
		return delegate.size();
	}

	public List<S> subList(int fromIndex, int toIndex) {
		return delegate.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return delegate.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}


} //SegmentListImpl
