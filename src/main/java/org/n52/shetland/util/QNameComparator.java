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
package org.n52.shetland.util;

import java.io.Serializable;
import java.util.Comparator;

import javax.xml.namespace.QName;

import org.n52.janmayen.Comparables;

/**
 * Comparator for {@link QName}s.
 *
 * @author <a href="mailto:c.autermann@52north.org">Christian Autermann</a>
 * @since 1.0.0
 * @deprecated use {@link Comparables#qname() }
 *
 */
@Deprecated
public class QNameComparator implements Comparator<QName>, Serializable {
    private static final long serialVersionUID = 1592187241620935158L;

    @Override
    public int compare(QName o1, QName o2) {
        return Comparables.qname().compare(o1, o2);
    }

}
