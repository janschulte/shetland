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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.n52.shetland.ogc.gml.AbstractFeature;
import org.n52.shetland.ogc.gml.CodeWithAuthority;
import org.n52.shetland.ogc.gml.time.IndeterminateValue;
import org.n52.shetland.ogc.gml.time.Time;
import org.n52.shetland.ogc.gml.time.TimeInstant;
import org.n52.shetland.ogc.gml.time.TimePeriod;
import org.n52.shetland.ogc.om.quality.OmResultQuality;
import org.n52.shetland.ogc.om.values.GeometryValue;
import org.n52.shetland.ogc.om.values.NilTemplateValue;
import org.n52.shetland.ogc.om.values.QuantityValue;
import org.n52.shetland.ogc.om.values.TVPValue;
import org.n52.shetland.ogc.om.values.Value;
import org.n52.shetland.util.CollectionHelper;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Class represents a SOS/O&M observation
 *
 * @since 4.0.0
 */
public class OmObservation extends AbstractFeature {

    /**
     * ID of this observation; in the standard 52n SOS PostgreSQL database, this
     * is implemented through a sequence type.
     */
    private String observationID;

    /**
     * result time of the observation.
     */
    private TimeInstant resultTime;

    /**
     * valid time of the observation.
     */
    private TimePeriod validTime;

    /**
     * constellation of procedure, obervedProperty, offering and observationType.
     */
    private OmObservationConstellation observationConstellation;

    /**
     * type of the value or the result the value points to.
     */
    private String resultType;

    /**
     * O&M parameter.
     */
    private Collection<NamedValue<?>> parameter;

    /**
     * Map with observation values for each obsservableProeprty.
     */
    private ObservationValue<?> value;

    /**
     * token separator for the value tuples contained in the result element of
     * the generic observation.
     */
    private String tokenSeparator;

    /**
     * no data value for the values contained in the result element.
     */
    private String noDataValue;

    /**
     * separator of value tuples, which are contained in the resulte element.
     */
    private String tupleSeparator;

    /**
     * separator of decimal values, which are contained in the resulte element.
     */
    private String decimalSeparator;

    /**
     * Measurment quality.
     */
    private Set<OmResultQuality> qualityList = Sets.newHashSet();

    private String additionalMergeIndicator;

    /**
     * constructor.
     */
    public OmObservation() {
        this("");
    }

    /**
     * constructor.
     *
     * @param identifier Feature identifier
     */
    public OmObservation(String identifier) {
        super(identifier);
    }

    /**
     * constructor.
     *
     * @param identifier Feature identifier
     */
    public OmObservation(CodeWithAuthority identifier) {
        super(identifier);
    }

    /**
     * constructor.
     *
     * @param identifier Feature identifier
     * @param gmlId      GML id
     */
    public OmObservation(CodeWithAuthority identifier, String gmlId) {
        super(identifier, gmlId);
    }

    /**
     * Get the observation constellation.
     *
     * @return the observationConstellation
     */
    public OmObservationConstellation getObservationConstellation() {
        return observationConstellation;
    }

    /**
     * Set the observation constellation.
     *
     * @param observationConstellation the observationConstellation to set
     */
    public void setObservationConstellation(OmObservationConstellation observationConstellation) {
        this.observationConstellation = observationConstellation;
    }

    /**
     * Get observation ID.
     *
     * @return the observationID
     */
    public String getObservationID() {
        return observationID;
    }

    /**
     * Set observation ID.
     *
     * @param observationID the observationID to set
     */
    public void setObservationID(final String observationID) {
        this.observationID = observationID;
    }

    /**
     * Get phenomenon time.
     *
     * @return the phenomenonTime
     */
    public Time getPhenomenonTime() {
        return value.getPhenomenonTime();
    }

    public boolean isSetPhenomenonTime() {
        return getPhenomenonTime() != null && !getPhenomenonTime().isEmpty();
    }

    /**
     * Get result time.
     *
     * @return the resultTime
     */
    public TimeInstant getResultTime() {
        return resultTime;
    }

    /**
     * Set result time.
     *
     * @param resultTime the resultTime to set
     */
    public void setResultTime(final TimeInstant resultTime) {
        this.resultTime = resultTime;
    }

    /**
     * Get valid time.
     *
     * @return the validTime
     */
    public TimePeriod getValidTime() {
        return validTime;
    }

    /**
     * Set valid time.
     *
     * @param validTime the validTime to set
     */
    public void setValidTime(final TimePeriod validTime) {
        this.validTime = validTime;
    }

    /**
     * Get result type.
     *
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * Set result type.
     *
     * @param resultType the resultType to set
     */
    public void setResultType(final String resultType) {
        this.resultType = resultType;
    }

    /**
     * Get token separator.
     *
     * @return the tokenSeparator
     */
    public String getTokenSeparator() {
        return tokenSeparator;
    }

    /**
     * Set token separator.
     *
     * @param tokenSeparator the tokenSeparator to set
     */
    public void setTokenSeparator(final String tokenSeparator) {
        this.tokenSeparator = tokenSeparator;
    }

    /**
     * Get noData value.
     *
     * @return the noDataValue
     */
    public String getNoDataValue() {
        return noDataValue;
    }

    /**
     * Set noData value.
     *
     * @param noDataValue the noDataValue to set
     */
    public void setNoDataValue(final String noDataValue) {
        this.noDataValue = noDataValue;
    }

    /**
     * Get tuple separator.
     *
     * @return the tupleSeparator
     */
    public String getTupleSeparator() {
        return tupleSeparator;
    }

    /**
     * Set tuple separator.
     *
     * @param tupleSeparator the tupleSeparator to set
     */
    public void setTupleSeparator(final String tupleSeparator) {
        this.tupleSeparator = tupleSeparator;
    }

    /**
     * Get decimal separator.
     *
     * @return the decimalSeparator
     */
    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    /**
     * Set decimal separator.
     *
     * @param decimalSeparator the decimalSeparator to set
     */
    public void setDecimalSeparator(final String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    /**
     * Get observation values.
     *
     * @return the values
     */
    public ObservationValue<?> getValue() {
        return value;
    }

    /**
     * Set observation values.
     *
     * @param value the values to set
     */
    public void setValue(final ObservationValue<?> value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return getValue() != null && getValue().isSetValue();
    }

    /**
     * Merge this observation with passed observation.
     *
     * @param sosObservation Observation to merge
     */
    public void mergeWithObservation(final OmObservation sosObservation) {
        mergeValues(sosObservation.getValue());
        mergeResultTimes(sosObservation);
    }

    /**
     * Merge this observation with passed observation.
     *
     * @param observationValue Observation to merge
     */
    public void mergeWithObservation(ObservationValue<?> observationValue) {
        mergeValues(observationValue);
    }


    /**
     * Merge result time with passed observation result time.
     *
     * @param sosObservation Observation to merge
     */
    private void mergeResultTimes(final OmObservation sosObservation) {
        if (isSetResultTime() && sosObservation.isSetResultTime()) {
            if (getResultTime().getValue().isBefore(sosObservation.getResultTime().getValue())) {
                resultTime = sosObservation.getResultTime();
            }
        } else if (!isSetResultTime() && sosObservation.isSetResultTime()) {
            resultTime = sosObservation.getResultTime();
        }
    }

    /**
     * Merge observation values with passed observation values.
     *
     * @param observationValue Observation to merge
     */
    private void mergeValues(final ObservationValue<?> observationValue) {
        TVPValue tvpValue;
        if (getValue() instanceof SingleObservationValue) {
            tvpValue = convertSingleValueToMultiValue((SingleObservationValue<?>) value);
        } else {
            tvpValue = (TVPValue) ((MultiObservationValues<?>) value).getValue();
        }
        if (observationValue instanceof SingleObservationValue) {
            final SingleObservationValue<?> singleValue = (SingleObservationValue<?>) observationValue;
            if (!(singleValue.getValue() instanceof NilTemplateValue)) {
                tvpValue.addValue(new TimeValuePair(singleValue.getPhenomenonTime(),
                                                    singleValue.getValue()));
            }
        } else if (observationValue instanceof MultiObservationValues) {
            tvpValue.addValues(((TVPValue) ((MultiObservationValues<?>) observationValue).getValue()).getValue());
        }
    }

    /**
     * Convert {@link SingleObservationValue} to {@link TVPValue}.
     *
     * @param singleValue Single observation value
     *
     * @return Converted TVPValue value
     */
    private TVPValue convertSingleValueToMultiValue(final SingleObservationValue<?> singleValue) {
        MultiObservationValues<List<TimeValuePair>> multiValue = new MultiObservationValues<>();
        TVPValue tvpValue = new TVPValue();
        tvpValue.setUnit(singleValue.getValue().getUnit());
        TimeValuePair timeValuePair = new TimeValuePair(singleValue.getPhenomenonTime(), singleValue.getValue());
        tvpValue.addValue(timeValuePair);
        multiValue.setValue(tvpValue);
        value = multiValue;
        return tvpValue;
    }

    /**
     * Check whether observation id is set.
     *
     * @return <code>true</code>, if observation id is set
     */
    public boolean isSetObservationID() {
        return getObservationID() != null && !getObservationID().isEmpty();
    }

    /**
     * Check whether tuple separator is set.
     *
     * @return <code>true</code>, if tuple separator is set
     */
    public boolean isSetTupleSeparator() {
        return getTupleSeparator() != null && !getTupleSeparator().isEmpty();
    }

    /**
     * Check whether token separator is set.
     *
     * @return <code>true</code>, if token separator is set
     */
    public boolean isSetTokenSeparator() {
        return getTokenSeparator() != null && !getTokenSeparator().isEmpty();
    }

    /**
     * Check whether decimal separator is set.
     *
     * @return <code>true</code>, if decimal separator is set
     */
    public boolean isSetDecimalSeparator() {
        return getDecimalSeparator() != null && !getDecimalSeparator().isEmpty();
    }

    /**
     * Check whether result time is set.
     *
     * @return <code>true</code>, if result time is set
     */
    public boolean isSetResultTime() {
        return resultTime != null && resultTime.isSetValue();
    }

    /**
     * Check whether result time is template is set.
     *
     * @return <code>true</code>, if result time is template is set
     */
    public boolean isTemplateResultTime() {
        return isSetResultTime() &&
               (getResultTime().isIndeterminateValueEqualTo(IndeterminateValue.TEMPLATE) ||
                getResultTime().isNilReasonEqualTo(Time.NilReason.template));
    }

    /**
     * Check whether result type is set.
     *
     * @return <code>true</code>, if result type is set
     */
    public boolean isSetResultType() {
        return getResultType() != null && !getResultType().isEmpty();
    }

    /**
     * Check whether valid time is set.
     *
     * @return <code>true</code>, if valid time is set
     */
    public boolean isSetValidTime() {
        return validTime != null && !validTime.isEmpty();
    }

    /**
     * Get parameter.
     *
     * @return the parameter
     */
    public Collection<NamedValue<?>> getParameter() {
        return parameter;
    }

    /**
     * Set parameter.
     *
     * @param parameter the parameter to set
     */
    public void setParameter(Collection<NamedValue<?>> parameter) {
        this.parameter = parameter;
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<Value<T>> getParameter(String name) {
        if (this.parameter == null) {
            return Optional.empty();
        }
        return this.parameter.stream()
                .filter(nv -> nv.getName().getHref().equals(name))
                .map(nv -> (Value<T>) nv.getValue())
                .findAny();
    }

    /**
     * Add parameter.
     *
     * @param namedValue the namedValue to add to parameter
     */
    public void addParameter(NamedValue<?> namedValue) {
        if (parameter == null) {
            parameter = Sets.newTreeSet();
        }
        parameter.add(namedValue);
    }

    /**
     * Check whether parameter is set.
     *
     * @return <code>true</code>, if parameter is set
     */
    public boolean isSetParameter() {
        return CollectionHelper.isNotEmpty(getParameter());
    }

    /**
     * Check whether spatial filtering profile parameter is set.
     *
     * @return <code>true</code>, if spatial filtering profile parameter is set
     */
    public boolean isSetSpatialFilteringProfileParameter() {
        return isSetParameter() ? getParameter().stream().anyMatch(this::isSamplingGeometryParameter) : false;
    }

    /**
     * Get spatial filtering profile parameter.
     *
     * @return Spatial filtering profile parameter
     */
    @SuppressWarnings("unchecked")
    public NamedValue<Geometry> getSpatialFilteringProfileParameter() {
        if (isSetParameter()) {
            for (NamedValue<?> namedValue : getParameter()) {
                if (isSamplingGeometryParameter(namedValue)) {
                    return (NamedValue<Geometry>) namedValue;
                }
            }
        }
        return null;
    }

    /**
     * Check whether sampling geometry for spatial filtering profile is set.
     *
     * @param namedValue the parameter
     *
     * @return <code>true</code>, if sampling geometry for spatial filtering profile is set
     */
    private boolean isSamplingGeometryParameter(NamedValue<?> namedValue) {
        return namedValue.isSetName() && namedValue.getName().isSetHref() &&
               namedValue.getName().getHref().equals(OmConstants.PARAM_NAME_SAMPLING_GEOMETRY) &&
               namedValue.getValue() instanceof GeometryValue;
    }

    /**
     * Check whether height parameter is set.
     *
     * @return <code>true</code>, if height parameter is set
     */
    public boolean isSetHeightParameter() {
        return isSetParameter() ? getParameter().stream().anyMatch(this::isHeightParameter) : false;
    }

    /**
     * Get height parameter.
     *
     * @return Height parameter
     */
    @SuppressWarnings("unchecked")
    public NamedValue<Double> getHeightParameter() {
        if (isSetParameter()) {
            for (NamedValue<?> namedValue : getParameter()) {
                if (isHeightParameter(namedValue)) {
                    return (NamedValue<Double>) namedValue;
                }
            }
        }
        return null;
    }

    private boolean isHeightParameter(NamedValue<?> namedValue) {
        return namedValue.isSetName() && namedValue.getName().isSetHref() &&
               namedValue.getName().getHref().equals(OmConstants.PARAMETER_NAME_HEIGHT) &&
               namedValue.getValue() instanceof QuantityValue;
    }

    /**
     * Check whether depth parameter is set.
     *
     * @return <code>true</code>, if depth parameter is set
     */
    public boolean isSetDepthParameter() {
        return (isSetParameter()) ? getParameter().stream().anyMatch(this::isDepthParameter) : false;
    }

    /**
     * Get depth parameter
     *
     * @return Depth parameter
     */
    @SuppressWarnings("unchecked")
    public NamedValue<Double> getDepthParameter() {
        if (isSetParameter()) {
            for (NamedValue<?> namedValue : getParameter()) {
                if (isHeightDepthParameter(namedValue)) {
                    return (NamedValue<Double>) namedValue;
                }
            }
        }
        return null;
    }

    private boolean isDepthParameter(NamedValue<?> namedValue) {
        return namedValue.isSetName() && namedValue.getName().isSetHref() &&
               namedValue.getName().getHref().equals(OmConstants.PARAMETER_NAME_DEPTH) &&
               namedValue.getValue() instanceof QuantityValue;
    }

    public boolean isSetHeightDepthParameter() {
        return isSetParameter() ? getParameter().stream().anyMatch(this::isHeightDepthParameter) : false;
    }

    public NamedValue<Double> getHeightDepthParameter() {
        return isSetDepthParameter() ? getDepthParameter() : getHeightParameter();
    }

    private boolean isHeightDepthParameter(NamedValue<?> namedValue) {
        return isHeightParameter(namedValue) || isDepthParameter(namedValue);
    }

    public OmObservation cloneTemplate() {
        OmObservation clone = new OmObservation();
        clone.setObservationConstellation(this.getObservationConstellation());
        clone.setParameter(this.getParameter());
        clone.setResultType(this.getResultType());
        clone.setTokenSeparator(this.getTokenSeparator());
        clone.setTupleSeparator(this.getTupleSeparator());
        clone.setDecimalSeparator(this.getDecimalSeparator());
        return clone;
    }

    @Override
    public String getGmlId() {
        if (Strings.isNullOrEmpty(super.getGmlId()) && isSetObservationID()) {
            setGmlId("o_" + getObservationID());
        }
        return super.getGmlId();
    }

    /**
     * Set result quality.
     *
     * @param qualityList Result quality to set
     *
     * @return {@code this}
     */
    public OmObservation setResultQuality(Set<OmResultQuality> qualityList) {
        this.qualityList = qualityList;
        return this;
    }

    public OmObservation addResultQuality(Set<OmResultQuality> qualityList) {
        this.qualityList.addAll(qualityList);
        return this;
    }

    public OmObservation addResultQuality(OmResultQuality qualityList) {
        this.qualityList.add(qualityList);
        return this;
    }

    /**
     * Get result quality.
     *
     * @return Result quality
     */
    public Set<OmResultQuality> getResultQuality() {
        return qualityList;
    }

    public boolean isSetResultQuality() {
        return CollectionHelper.isNotEmpty(getResultQuality());
    }

    public OmObservation setAdditionalMergeIndicator(String additionalMergeIndicator) {
        this.additionalMergeIndicator = additionalMergeIndicator;
        return this;
    }

    public String getAdditionalMergeIndicator() {
        return additionalMergeIndicator;
    }

    public boolean isSetAdditionalMergeIndicator() {
        return getAdditionalMergeIndicator() != null && !getAdditionalMergeIndicator().isEmpty();
    }

    public boolean checkForMerge(OmObservation observation) {
        boolean merge = true;
        if (isSetAdditionalMergeIndicator() && observation.isSetAdditionalMergeIndicator()) {
            merge = getAdditionalMergeIndicator().equals(observation.getAdditionalMergeIndicator());
        } else if ((isSetAdditionalMergeIndicator() && !observation.isSetAdditionalMergeIndicator()) ||
                   (!isSetAdditionalMergeIndicator() && observation.isSetAdditionalMergeIndicator())) {
            merge = false;
        }
        return getObservationConstellation().equals(observation.getObservationConstellation()) && merge &&
               getObservationConstellation().checkObservationTypeForMerging();
    }

}
