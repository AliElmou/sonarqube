/*
 * SonarQube
 * Copyright (C) 2009-2025 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.core.quality;

public class MethodValid {

    // Limite pour la longueur d'une ligne de code
    private static final int MAX_LINE_LENGTH = 80;

    // Limite pour le nombre de paramètres dans une méthode
    private static final int MAX_PARAMETERS = 5;

    // Méthode pour vérifier si une ligne de code est trop longue
    public static boolean isMethodLengthValid(String methodCode) {
        if (methodCode == null || methodCode.isEmpty()) {
            return true;
        }

        String[] lines = methodCode.split("\n");
        for (String line : lines) {
            if (line.length() > MAX_LINE_LENGTH) {
                return false;
            }
        }
        return true;
    }

    // Méthode pour vérifier le nombre de paramètres d'une méthode
    public static boolean isMethodParameterCountValid(String methodCode) {
        if (methodCode == null || methodCode.isEmpty()) {
            return true;
        }

        // Extraire les parametre de la méthode
        String[] lines = methodCode.split("\n");
        for (String line : lines) {
            if (line.contains("(") && line.contains(")")) {
                int startIndex = line.indexOf('(');
                int endIndex = line.indexOf(')');
                String params = line.substring(startIndex + 1, endIndex).trim();
                if (params.isEmpty()) {
                    return true;
                }
                String[] parameters = params.split(",");
                return parameters.length <= MAX_PARAMETERS;
            }
        }
        return true;
    }
}
