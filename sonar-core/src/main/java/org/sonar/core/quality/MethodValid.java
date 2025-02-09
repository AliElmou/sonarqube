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
