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
package org.n52.shetland.ogc.sos;

import org.n52.shetland.ogc.sos.SosProcedureDescription;
import org.n52.shetland.ogc.gml.AbstractFeature;

/**
 * @since 4.0.0
 *
 */
public class SosProcedureDescriptionUnknownType extends SosProcedureDescription<AbstractFeature> {

    public SosProcedureDescriptionUnknownType(String identifier, String procedureDescriptionFormat, String xmlDescription) {
        super(new UnknownGMLDescription(identifier, procedureDescriptionFormat, xmlDescription));
    }

    public SosProcedureDescriptionUnknownType(String identifier) {
        super(new UnknownGMLDescription(identifier, null, null));
    }

    private static class UnknownGMLDescription extends AbstractFeature {
        private final String description;

        public UnknownGMLDescription(String identifier, String procedureDescriptionFormat, String xmlDescription) {
            super(identifier);
            setDefaultElementEncoding(procedureDescriptionFormat);
            this.description = xmlDescription;
        }

        public String getXmlDescription() {
            return this.description;
        }

    }

}