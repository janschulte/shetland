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
package org.n52.shetland.ogc.swe.simpleType;


import java.util.Collection;

import org.n52.shetland.ogc.ows.extension.Value;
import org.n52.shetland.ogc.swe.SweAbstractDataComponent;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * Interface for the SOS internal representation of SWE simpleTypes
 *
 * @param <T>
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.0.0
 */
public abstract class SweAbstractSimpleType<T> extends SweAbstractDataComponent
        implements Value<T, SweAbstractSimpleType<T>> {

    // TODO quality needs to be a collection
    private Collection<SweQuality> quality;

    /**
     * Get quality information
     *
     * @return Quality information
     */
    public Collection<SweQuality> getQuality() {
        return quality;
    }

    /**
     * Set quality information
     *
     * @param quality
     *            quality information to set
     * @return This SweAbstractSimpleType
     */
    public SweAbstractSimpleType<T> setQuality(Collection<SweQuality> quality) {
        this.quality = quality;
        return this;
    }

    /**
     * @return <tt>true</tt>, if the quality field is not <tt>null</tt>,<br>
     *         <tt>false</tt> else.
     */
    public boolean isSetQuality() {
        return quality != null && !quality.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getValue());
    }

    @Override
    public String toString() {
        return String.format("%s [value=%s; quality=%s; simpleType=%s]", this.getClass().getSimpleName(), getValue(),
                getQuality(), getDataComponentType());
    }

    protected Collection<SweQuality> cloneQuality() {
        if (isSetQuality()) {
            return Lists.newArrayList(getQuality());
        }
        return null;
    }

}
