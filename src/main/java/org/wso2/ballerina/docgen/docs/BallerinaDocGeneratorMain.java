/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.ballerina.docgen.docs;

import org.wso2.ballerina.core.model.Package;
import org.wso2.ballerina.core.model.annotations.BallerinaPrimitive;
import org.wso2.ballerina.docgen.docs.html.HtmlDocumentWriter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * Main class to generate a ballerina documentation
 */
public class BallerinaDocGeneratorMain {

    private static PrintStream out = System.out;

    public static void main(String[] args) {

        if ((args == null) || (args.length < 1 || args.length > 2)) {
            out.println("Usage: docerina [ballerina-package-path] [package-filter]");
            return;
        }

        try {
            String packagePath = args[0];
            String packageFilter = (args.length == 2) ? args[1] : "";
            Map<String, Package> docsMap = BallerinaDocGenerator.generatePackageDocsFromBallerina(
                    packagePath, packageFilter);
            List<BallerinaPrimitive> primitives = BallerinaDocGenerator.findPrimitiveTypes();

            HtmlDocumentWriter htmlDocumentWriter = new HtmlDocumentWriter();
            htmlDocumentWriter.write(docsMap.values(), primitives);
        } catch (IOException | ClassNotFoundException e) {
            out.println("Docerina: Could not generate HTML API documentation: " + e.getMessage());
        }
    }
}
