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
package org.n52.shetland.ogc.om;

import org.n52.shetland.ogc.sos.Sos2Constants;
import org.n52.shetland.ogc.sos.response.AbstractStreaming;
import org.n52.shetland.util.GeometryTransformer;

import javax.inject.Inject;

import org.n52.shetland.ogc.gml.time.Time;
import org.n52.shetland.ogc.gml.time.TimeInstant;
import org.n52.shetland.ogc.om.values.Value;
import org.n52.shetland.ogc.ows.OWSConstants.AdditionalRequestParams;
import org.n52.shetland.ogc.ows.exception.OwsExceptionReport;
//import org.n52.sos.util.GeometryHandler;

import com.vividsolutions.jts.geom.Geometry;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract streaming value class
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.1.0
 *
 * @param <S>
 *            Entity type
 */
public abstract class StreamingValue<S> extends AbstractStreaming {
    private static final long serialVersionUID = -884370769373807775L;
    private Time phenomenonTime;
    private TimeInstant resultTime;
    private Time validTime;
    private String unit;
    private boolean unitQueried = false;
    private OmObservation observationTemplate;
    private GeometryTransformer geometryTransformer;

    /**
     * Get the next entity
     *
     * @return next entity object
     * @throws OwsExceptionReport
     *             If an error occurs
     */
    public abstract S nextEntity() throws OwsExceptionReport;

    /**
     * Query times
     */
    protected abstract void queryTimes();

    /**
     * query unit
     */
    protected abstract void queryUnit();

    /**
     * Get next {@link TimeValuePair} from entity
     *
     * @return Next {@link TimeValuePair}
     * @throws OwsExceptionReport
     *             If an error occurs
     */
    public abstract TimeValuePair nextValue() throws OwsExceptionReport;

    protected GeometryTransformer getGeometryTransformer() {
        return geometryTransformer;
    }

    /**
     * @param geometryTransformer the geometryTransformer to set
     */
    @Inject
    public void setGeometryTransformer(GeometryTransformer geometryTransformer) {
        this.geometryTransformer = geometryTransformer;
    }

    /**
     * Set the observation template which contains all metadata
     *
     * @param observationTemplate
     *            Observation template to set
     */
    public void setObservationTemplate(OmObservation observationTemplate) {
        this.observationTemplate = observationTemplate;
    }

    /**
     * @return the observationTemplate
     */
    public OmObservation getObservationTemplate() {
        return observationTemplate;
    }

    @Override
    public Time getPhenomenonTime() {
        isSetPhenomenonTime();
        return phenomenonTime;
    }

    @Override
    public void setPhenomenonTime(Time phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public boolean isSetPhenomenonTime() {
        if (phenomenonTime == null) {
            queryTimes();
        }
        return phenomenonTime != null;
    }

    @Override
    public Value<OmObservation> getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setValue(Value<OmObservation> value) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getUnit() {
        isSetUnit();
        return unit;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
        unitQueried = true;
    }

    @Override
    public boolean isSetUnit() {
        if (!unitQueried && unit == null) {
            queryUnit();
            unitQueried = true;
        }
        return unit != null;
    }

    public TimeInstant getResultTime() {
        return resultTime;
    }

    protected void setResultTime(TimeInstant resultTime) {
        this.resultTime = resultTime;
    }

    public boolean isSetResultTime() {
        return getResultTime() != null;
    }

    public Time getValidTime() {
        return validTime;
    }

    protected void setValidTime(Time validTime) {
        this.validTime = validTime;
    }

    public boolean isSetValidTime() {
        return getValidTime() != null;
    }

    @SuppressWarnings("unchecked")
    @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE")
    @Override
    protected void checkForModifications(OmObservation observation) throws OwsExceptionReport {
        if (isSetAdditionalRequestParams() && contains(AdditionalRequestParams.crs)) {
            Object additionalRequestParam = getAdditionalRequestParams(AdditionalRequestParams.crs);
            int targetCRS = -1;
            if (additionalRequestParam instanceof Integer) {
                targetCRS = (Integer) additionalRequestParam;
            } else if (additionalRequestParam instanceof String) {
                targetCRS = Integer.parseInt((String) additionalRequestParam);
            }
            if (observation.isSetParameter()) {
                for (NamedValue<?> namedValue : observation.getParameter()) {
                    if (getGeometryTransformer() != null && Sos2Constants.HREF_PARAMETER_SPATIAL_FILTERING_PROFILE.equals(namedValue.getName().getHref())) {
                        NamedValue<Geometry> spatialFilteringProfileParameter = (NamedValue<Geometry>) namedValue;
                        spatialFilteringProfileParameter.getValue().setValue(
                                getGeometryTransformer().transform(
                                        spatialFilteringProfileParameter.getValue().getValue(), targetCRS));
                    }
                }
            }
        }
    }

}
