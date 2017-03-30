/*
 * Copyright 2016-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.shetland.ogc.om.values;

import java.util.List;
import java.util.Objects;

import org.n52.shetland.ogc.swe.SweAbstractDataComponent;
import org.n52.shetland.ogc.swe.SweDataRecord;
import org.n52.shetland.ogc.swe.SweField;

import com.google.common.collect.Lists;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Represents the level of a profile
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.4.0
 *
 */
public class ProfileLevel implements Comparable<ProfileLevel> {

    private QuantityValue levelStart;
    private QuantityValue levelEnd;
    private List<Value<?>> value = Lists.newArrayList();
    private Geometry location;

    /**
     * constructor
     */
    public ProfileLevel() {
        super();
    }

    /**
     * constructor
     *
     * @param levelStart
     *            the levelStart value
     * @param toDepth
     *            the toDepth value
     * @param value
     *            the values
     */
    public ProfileLevel(QuantityValue levelStart, QuantityValue levelEnd, List<Value<?>> value) {
        super();
        this.levelStart = levelStart;
        this.levelEnd = levelEnd;
        this.value = value;
    }

    /**
     * @return the levelStart
     */
    public QuantityValue getLevelStart() {
        return levelStart;
    }

    /**
     * @param levelStart
     *            the levelStart to set
     */
    public ProfileLevel setLevelStart(QuantityValue levelStart) {
        this.levelStart = levelStart;
        return this;
    }

    public boolean isSetLevelStart() {
        return getLevelStart() != null;
    }

    /**
     * @return the levelEnd
     */
    public QuantityValue getLevelEnd() {
        return levelEnd;
    }

    /**
     * @param levelEnd
     *            the levelEnd to set
     */
    public ProfileLevel setLevelEnd(QuantityValue levelEnd) {
        this.levelEnd = levelEnd;
        return this;
    }

    public boolean isSetLevelEnd() {
        return getLevelEnd() != null;
    }

    /**
     * @return the value
     */
    public List<Value<?>> getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public ProfileLevel setValue(List<Value<?>> value) {
        this.value.clear();
        this.value.addAll(value);
        return this;
    }

    /**
     * @param value
     *            the value to set
     */
    public ProfileLevel addValue(Value<?> value) {
        this.value.add(value);
        return this;
    }

    public boolean isSetValue() {
        return getValue() != null;
    }

    /**
     * @return the simpleValue
     */
    public Value<?> getSimpleValue() {
        return value.iterator().next();
    }

    /**
     * @return the location
     */
    public Geometry getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public ProfileLevel setLocation(Geometry location) {
        this.location = location;
        return this;
    }

    public boolean isSetLocation() {
        return getLocation() != null;
    }

    @Override
    public int compareTo(ProfileLevel o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (getLevelStart() == null ^ o.getLevelStart() == null) {
            return (getLevelStart() == null) ? -1 : 1;
        }
        if (getLevelStart() == null && o.getLevelStart() == null) {
            return 0;
        }
        return this.getLevelStart().equals(o.getLevelStart()) ? 0 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfileLevel other = (ProfileLevel) obj;
        if ((getLevelStart() == null) ? (other.getLevelStart() != null) : !getLevelStart().equals(other.getLevelStart())) {
            return false;
        }
        if ((getLevelEnd() == null) ? (other.getLevelEnd() != null) : !getLevelEnd().equals(other.getLevelEnd())) {
            return false;
        }
        if ((getLocation() == null) ? (other.getLocation() != null) : !getLocation().equals(other.getLocation())) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + Objects.hashCode(this.getLocation());
        hash = 37 * hash + Objects.hashCode(this.getLevelStart());
        hash = 37 * hash + Objects.hashCode(this.getLevelEnd());
        hash = 37 * hash + Objects.hashCode(this.getValue());
        return hash;
    }

    public SweDataRecord asDataRecord() {
        SweDataRecord dataRecord = new SweDataRecord();
        if (isSetLevelStart()) {
            dataRecord.addField(new SweField(getLevelStart().getName(), getLevelStart()));
        }
        if (isSetLevelStart()) {
            dataRecord.addField(new SweField(getLevelStart().getName(), getLevelStart()));
        }
        return valueAsDataRecord(dataRecord);
    }

    public SweDataRecord valueAsDataRecord() {
        return valueAsDataRecord(new SweDataRecord());
    }

    public SweDataRecord valueAsDataRecord(SweDataRecord dataRecord) {
        int counter = 0;
        for (Value<?> value : getValue()) {
            if (value instanceof SweAbstractDataComponent) {
                SweAbstractDataComponent adc = (SweAbstractDataComponent) value;
                String name = "";
                if (adc.isSetName()) {
                    name = adc.getName().getValue();
                } else if (adc.isSetDefinition()) {
                    name = adc.getDefinition();
                } else {
                    name = "component_" + counter++;
                }
                dataRecord.addField(new SweField(name, adc));
            }
        }
        return dataRecord;
    }
}