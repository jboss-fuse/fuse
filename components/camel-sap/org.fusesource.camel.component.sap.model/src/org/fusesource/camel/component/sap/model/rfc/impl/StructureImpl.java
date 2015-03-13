/**
 * Copyright 2013 Red Hat, Inc.
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
 *  implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 */
package org.fusesource.camel.component.sap.model.rfc.impl;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.fusesource.camel.component.sap.model.rfc.RfcPackage;
import org.fusesource.camel.component.sap.model.rfc.Structure;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl#getFieldCount <em>Field Count</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl#getRecordLength <em>Record Length</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl#getUnicodeRecordLength <em>Unicode Record Length</em>}</li>
 *   <li>{@link org.fusesource.camel.component.sap.model.rfc.impl.StructureImpl#isNestedType1Structure <em>Nested Type1 Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructureImpl extends EObjectImpl implements Structure {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getFieldCount() <em>Field Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldCount()
	 * @generated
	 * @ordered
	 */
	protected static final int FIELD_COUNT_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getRecordLength() <em>Record Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecordLength()
	 * @generated
	 * @ordered
	 */
	protected static final int RECORD_LENGTH_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #getUnicodeRecordLength() <em>Unicode Record Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnicodeRecordLength()
	 * @generated
	 * @ordered
	 */
	protected static final int UNICODE_RECORD_LENGTH_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #isNestedType1Structure() <em>Nested Type1 Structure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNestedType1Structure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NESTED_TYPE1_STRUCTURE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RfcPackage.Literals.STRUCTURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getName() {
		String name = null;
		EAnnotation idocAnnotation = eClass().getEAnnotation(RfcPackage.eNS_URI);
		if (idocAnnotation != null) {
			name = idocAnnotation.getDetails().get("name");
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setName(String newName) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getFieldCount() {
		int fieldCount = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(RfcPackage.eNS_URI);
		if (idocAnnotation != null) {
			String fieldCountString = idocAnnotation.getDetails().get("fieldCount");
			try {
				fieldCount = Integer.parseInt(fieldCountString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return fieldCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setFieldCount(int newFieldCount) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getRecordLength() {
		int recordLength = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(RfcPackage.eNS_URI);
		if (idocAnnotation != null) {
			String recordLengthString = idocAnnotation.getDetails().get("recordLength");
			try {
				recordLength = Integer.parseInt(recordLengthString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return recordLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setRecordLength(int newRecordLength) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getUnicodeRecordLength() {
		int unicodeRecordLength = -1;
		EAnnotation idocAnnotation = eClass().getEAnnotation(RfcPackage.eNS_URI);
		if (idocAnnotation != null) {
			String unicodeRecordLengthString = idocAnnotation.getDetails().get("unicodeRecordLength");
			try {
				unicodeRecordLength = Integer.parseInt(unicodeRecordLengthString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return unicodeRecordLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setUnicodeRecordLength(int newUnicodeRecordLength) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isNestedType1Structure() {
		boolean isNestedType1Structure = false;
		EAnnotation idocAnnotation = eClass().getEAnnotation(RfcPackage.eNS_URI);
		if (idocAnnotation != null) {
			String isNestedType1StructureString = idocAnnotation.getDetails().get("isNestedType1Structure");
			try {
				isNestedType1Structure = Boolean.parseBoolean(isNestedType1StructureString);
			} catch (NumberFormatException e) {
				// TODO log warning.
			}
		}
		return isNestedType1Structure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setNestedType1Structure(boolean newIsNestedType1Structure) {
		// NOOP
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public <T> T get(Object key, Class<T> type) {
		Object value = get(key);
		if (value == null) {
			return null;
		}
		if (type.isInstance(value)) {
			return type.cast(value);
		} else {
			throw new IllegalArgumentException("The value is not of type: " + type + " but is : " + value.getClass().getCanonicalName());
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RfcPackage.STRUCTURE__NAME:
				return getName();
			case RfcPackage.STRUCTURE__FIELD_COUNT:
				return getFieldCount();
			case RfcPackage.STRUCTURE__RECORD_LENGTH:
				return getRecordLength();
			case RfcPackage.STRUCTURE__UNICODE_RECORD_LENGTH:
				return getUnicodeRecordLength();
			case RfcPackage.STRUCTURE__NESTED_TYPE1_STRUCTURE:
				return isNestedType1Structure();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RfcPackage.STRUCTURE__NAME:
				setName((String)newValue);
				return;
			case RfcPackage.STRUCTURE__FIELD_COUNT:
				setFieldCount((Integer)newValue);
				return;
			case RfcPackage.STRUCTURE__RECORD_LENGTH:
				setRecordLength((Integer)newValue);
				return;
			case RfcPackage.STRUCTURE__UNICODE_RECORD_LENGTH:
				setUnicodeRecordLength((Integer)newValue);
				return;
			case RfcPackage.STRUCTURE__NESTED_TYPE1_STRUCTURE:
				setNestedType1Structure((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RfcPackage.STRUCTURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RfcPackage.STRUCTURE__FIELD_COUNT:
				setFieldCount(FIELD_COUNT_EDEFAULT);
				return;
			case RfcPackage.STRUCTURE__RECORD_LENGTH:
				setRecordLength(RECORD_LENGTH_EDEFAULT);
				return;
			case RfcPackage.STRUCTURE__UNICODE_RECORD_LENGTH:
				setUnicodeRecordLength(UNICODE_RECORD_LENGTH_EDEFAULT);
				return;
			case RfcPackage.STRUCTURE__NESTED_TYPE1_STRUCTURE:
				setNestedType1Structure(NESTED_TYPE1_STRUCTURE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RfcPackage.STRUCTURE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case RfcPackage.STRUCTURE__FIELD_COUNT:
				return getFieldCount() != FIELD_COUNT_EDEFAULT;
			case RfcPackage.STRUCTURE__RECORD_LENGTH:
				return getRecordLength() != RECORD_LENGTH_EDEFAULT;
			case RfcPackage.STRUCTURE__UNICODE_RECORD_LENGTH:
				return getUnicodeRecordLength() != UNICODE_RECORD_LENGTH_EDEFAULT;
			case RfcPackage.STRUCTURE__NESTED_TYPE1_STRUCTURE:
				return isNestedType1Structure() != NESTED_TYPE1_STRUCTURE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public static class Entry implements Map.Entry<String,Object> {
		
		private String key;
		private Object value;
		
		Entry(String key, Object value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public Object setValue(Object newValue) {
            Object oldValue = value;
            value = newValue;
            return oldValue;
		}
		
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            @SuppressWarnings("unchecked")
			Map.Entry<Object,Object> e = (Map.Entry<Object,Object>)o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }
        
        public final int hashCode() {
            return (key==null   ? 0 : key.hashCode()) ^
                   (value==null ? 0 : value.hashCode());
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
	}
	
	private abstract class FeatureIterator<E> implements Iterator<E> {
		
		private final Iterator<EStructuralFeature> iterator;
		private EStructuralFeature current;
		
		public FeatureIterator() {
			iterator = eClass().getEStructuralFeatures().iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		EStructuralFeature nextEntry() {
			current = iterator.next(); 
			return current;
		}

		@Override
		public void remove() {
			if (current == null) 
				throw new IllegalStateException();
			String key = current.getName();
			current = null;
			StructureImpl.this.remove(key);
		}
		
	}
	
	private class ValueIterator extends FeatureIterator<Object> {
		@Override
		public Object next() {
			EStructuralFeature feature = nextEntry();
			return get(feature.getName());
		}
	}
	
	private class KeyIterator extends FeatureIterator<String> {
		@Override
		public String next() {
			return nextEntry().getName();
		}
	}
	
	private class EntryIterator extends FeatureIterator<java.util.Map.Entry<String, Object>> {
		@Override
		public Entry next() {
			EStructuralFeature feature = nextEntry();
			Object value = get(feature.getName());
			return new Entry(feature.getName(), value);
		}
	}
	
	private class EntrySet extends AbstractSet<java.util.Map.Entry<String, Object>> {

		@Override
		public Iterator<java.util.Map.Entry<String, Object>> iterator() {
			return new EntryIterator();
		}
		
		@Override
		public boolean contains(Object o) {
            if (!(o instanceof Entry))
                return false;
            Entry e = (Entry)o;
            return StructureImpl.this.containsKey(e.getKey());
		}
		
		@Override
		public boolean remove(Object o) {
            if (!(o instanceof Entry))
                return false;
            Entry e = (Entry)o;
            Object value = StructureImpl.this.remove(e.getKey());
            return value != null; 
		}

		@Override
		public int size() {
			return StructureImpl.this.size();
		}
		
		@Override
		public void clear() {
			StructureImpl.this.clear();
		}
		
	}

	private class KeySet extends AbstractSet<String> {

		@Override
		public Iterator<String> iterator() {
			return new KeyIterator();
		}

		@Override
		public int size() {
			return StructureImpl.this.size();
		}
		
		@Override
		public boolean contains(Object key) {
			return containsKey(key);
		}
		
		@Override
		public boolean remove(Object key) {
			if (containsKey(key)) {
				StructureImpl.this.remove(key);
				return true;
			}
			else
				return false;
		}
		
		@Override
		public void clear() {
			StructureImpl.this.clear();
		}
		
	}

    private class Values extends AbstractCollection<Object> {

		@Override
		public Iterator<Object> iterator() {
			return new ValueIterator();
		}

		@Override
		public int size() {
			return StructureImpl.this.size();
		}
		
		@Override
		public boolean contains(Object o) {
            return containsValue(o);
		}
		
		@Override
		public void clear() {
			StructureImpl.this.clear();
		}
    }	
    
	private transient volatile EntrySet entrySet = null;
	
	private transient volatile KeySet keySet = null;
	
	private transient volatile Values values = null;

	@Override
	public int size() {
		return eClass().getFeatureCount();
	}

	@Override
	public boolean isEmpty() {
		return eClass().getFeatureCount() == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof String)) 
			return false;
		String featureName = (String) key;
		return eClass().getEStructuralFeature(featureName) == null ? false : true;
	}

	@Override
	public boolean containsValue(Object value) {
		for(EStructuralFeature eFeature: eClass().getEStructuralFeatures()) {
			if (eGet(eFeature).equals(value))
				return true;
		}
		return false;
	}

	@Override
	public Object get(Object key) {
		if (!(key instanceof String)) 
			return null;
		String featureName = (String) key;
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureName);
		if (eFeature == null)
			return null;
		Object value = eGet(eFeature);
		if (value == null && eFeature instanceof EReference) {
			// Populate empty Structure or Table
			EClass eClass = ((EReference) eFeature).getEReferenceType();
			value = eClass.getEPackage().getEFactoryInstance()
					.create(eClass);
			put(featureName, value);
		}
		return value;
	}

	@Override
	public Object put(String key, Object value) {
		if (key == null) 
			throw new NullPointerException("Key can not be null");
		EStructuralFeature eFeature = eClass().getEStructuralFeature(key);
		if (eFeature == null)
			throw new IllegalArgumentException("Key '" + key + "' is not valid for this structure");
		Object returnValue = eGet(eFeature);
		eSet(eFeature, value);
		return returnValue;
	}

	@Override
	public Object remove(Object key) {
		if (!(key instanceof String)) 
			return null;
		String featureName = (String) key;
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureName);
		if (eFeature == null)
			return null;
		Object returnValue = eGet(eFeature);
		eUnset(eFeature);
		return returnValue;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for(String key: m.keySet()) {
			Object value = m.get(key);
			put(key, value);
		}
	}

	@Override
	public void clear() {
		for(EStructuralFeature eFeature: eClass().getEStructuralFeatures()) {
			eUnset(eFeature);
		}
	}

	@Override
	public Set<String> keySet() {
		KeySet ks = keySet;
		return (ks != null ? ks : (keySet = new KeySet()));
	}

	@Override
	public Collection<Object> values() {
		Values vs = values;
		return (vs != null ? vs : (values = new Values()));
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		EntrySet es = entrySet;
		return (es != null ? es : (entrySet = new EntrySet()));
	}

} //StructureImpl
