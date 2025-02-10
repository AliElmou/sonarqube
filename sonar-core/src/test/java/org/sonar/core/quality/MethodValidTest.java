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

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MethodValidTest {
    @Test
    public void testIsMethodLengthValid_ValidLines() {
        // Méthode avec des lignes de code valides
        String methodCode = "public void myMethod() {\n" +
                "    int a = 5;\n" +
                "    int b = 10;\n" +
                "    System.out.println(a + b);\n" +
                "}";

        assertTrue(MethodValid.isMethodLengthValid(methodCode), "Toutes les lignes de la méthode sont valides.");
    }

    @Test
    public void testIsMethodLengthValid_InvalidLine() {
        // Méthode avec une ligne de code trop longue
        String methodCode = "public void myMethod() {\n" +
                "    int a = 5;\n" +
                "    int b = 10;\n" +
                "    System.out.println(\"This line is way too long and should trigger a warning because it exceeds the maximum allowed length.\");\n" +
                "}";

        assertFalse(MethodValid.isMethodLengthValid(methodCode), "Une ligne de la méthode est trop longue.");
    }

    @Test
    public void testIsMethodParameterCountValid_WithinLimit() {
        // Méthode avec un nombre de paramètres acceptable
        String methodCode = "public void myMethod(int a, String b, double c) {\n" +
                "    System.out.println(a + b + c);\n" +
                "}";

        assertTrue(MethodValid.isMethodParameterCountValid(methodCode), "Une méthode avec 3 paramètres devrait être valide.");
    }

    @Test
    public void testIsMethodParameterCountValid_ExceedsLimit() {
        // Méthode avec un nombre de paramètres trop élevé
        String methodCode = "public void complexMethod(int a, String b, double c, int d, String e, int f) {\n" +
                "    System.out.println(a + b + c + d + e + f);\n" +
                "}";

        assertFalse(MethodValid.isMethodParameterCountValid(methodCode), "Une méthode avec 6 paramètres devrait être invalide.");
    }

    @Test
    public void testIsMethodParameterCountValid_NoParameters() {
        // Méthode sans paramètres
        String methodCode = "public void myMethod() {\n" +
                "    System.out.println(\"Hello, world!\");\n" +
                "}";

        assertTrue(MethodValid.isMethodParameterCountValid(methodCode), "Une méthode sans paramètres devrait être valide.");
    }

    @Test
    public void testIsMethodLengthValid_EmptyCode() {
        // Méthode avec un code vide
        assertTrue(MethodValid.isMethodLengthValid(""), "Une méthode avec un code vide devrait être valide.");
    }
}
