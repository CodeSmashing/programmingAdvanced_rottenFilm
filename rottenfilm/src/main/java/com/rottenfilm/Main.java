package com.rottenfilm;

public class Main {
    // Constants
    public static final String MAIN_PATH = "./rottenfilm/src/main/java/com/rottenfilm/";
    public static final String DATA_PATH = "./rottenfilm/src/main/resources/";

    public static void main(String[] args) {
        MovieCatalog movieCatalog = new MovieCatalog(DATA_PATH + "netflix_titles.csv");
        UserInterface ui = new UserInterface(movieCatalog);
        ui.clearTerminal();
        ui.run();
    }
}


// // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
// // Print the records
// ArrayList<HashMap<String, String>> moviesArray = new ArrayList<>();
// for (HashMap<String, String> movieHashMap : moviesArray) {
//     // // To write out all the keys and values
//     // for (Map.Entry<String, String> record : movieHashMap.entrySet()) {
//     //     String key = record.getKey();
//     //     String value = record.getValue();

//     //     System.out.println(key + " " +  value);
//     //     // System.out.println(String.join(", ", record));
//     // }
// }


// // Format a string into a date
// // https://stackoverflow.com/questions/8746084/string-to-localdate
// DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd"); // LocalDate.parse(values[i], format);
// String[] subArrayValues = values[i].replaceAll("\\\"*", "").split(" ");
// String newValue = "";
// newValue += subArrayValues[2] + "-";
// newValue += subArrayValues[0].substring(0, 3).toLowerCase() + "-";
// newValue += subArrayValues[1];
// values[i] = newValue;
