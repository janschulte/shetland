/*
 * Copyright 2016 52°North Initiative for Geospatial Open Source
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
package org.n52.shetland.ogc.sensorML;

import java.util.ArrayList;
import java.util.List;

import org.n52.shetland.ogc.gml.EngineeringCRS;
import org.n52.shetland.ogc.sensorML.elements.SmlComponent;

/**
 * @since 4.0.0
 *
 */
public class System extends AbstractComponent implements HasComponents<System> {

    private EngineeringCRS spatialReferenceFrame;

    private final List<SmlComponent> components = new ArrayList<SmlComponent>(0);

    @Override
    public List<SmlComponent> getComponents() {
        return components;
    }

    @Override
    public System addComponents(final List<SmlComponent> components) {
        if (components != null) {
            checkAndSetChildProcedures(components);
            this.components.addAll(components);
        }
        return this;
    }

    @Override
    public System addComponent(final SmlComponent component) {
        if (component != null) {
            checkAndSetChildProcedures(component);
            components.add(component);
        }
        return this;
    }

    @Override
    public boolean isSetComponents() {
        return components != null && !components.isEmpty();
    }

    @Override
    public boolean isAggragation() {
        return true;
    }

}